package org.sawtooth.compiler.realizations;

import org.sawtooth.compiler.configuration.models.CompilerConfiguration;
import org.sawtooth.compiler.models.CompileResults;
import org.sawtooth.configuration.models.LanguageConfiguration;
import org.sawtooth.launcher.configuration.models.LauncherConfiguration;

import java.io.File;
import java.io.IOException;

public class CompileThread extends Thread {
    private final LanguageConfiguration configuration;
    private final CompileResults compileResults;
    private final boolean isOnWindows;
    private final String sourcesPath;

    CompileThread(boolean isOnWindows, String name, String sourcesPath, LanguageConfiguration configuration){
        super(name);
        this.isOnWindows = isOnWindows;
        this.configuration = configuration;
        this.sourcesPath = sourcesPath;
        compileResults = new CompileResults();
    }

    public CompileResults getCompileResults() {
        return compileResults;
    }

    private void ReadCompileErrors(Process process) throws IOException {
        String temp;

        while((temp = process.errorReader().readLine()) != null)
            compileResults.err.add(temp);
    }

    private void ReadCompileOutput(Process process) throws IOException {
        String temp;

        while((temp = process.inputReader().readLine()) != null)
            compileResults.out.add(temp);
    }

    private void ExecuteCommand(ProcessBuilder processBuilder, String command) throws IOException, InterruptedException {
        Process process;

        if (isOnWindows)
            processBuilder.command("cmd.exe", "/c", command);
        else
            processBuilder.command("sh", "-c", command);
        processBuilder.directory(new File(System.getProperty("user.dir")));
        process = processBuilder.start();
        compileResults.exitCode = process.waitFor();
        ReadCompileErrors(process);
        ReadCompileOutput(process);
    }

    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        for (String command : configuration.compilingCommands) {
            try {
                ExecuteCommand(processBuilder, command.replace("{{name}}", getName()).replace("{{path}}", sourcesPath));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
