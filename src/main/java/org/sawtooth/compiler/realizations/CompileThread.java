package org.sawtooth.compiler.realizations;

import org.sawtooth.compiler.configuration.CompilerConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CompileThread extends Thread{
    private final CompilerConfiguration configuration;
    private final CompileResults compileResults;

    CompileThread(String name, CompilerConfiguration configuration){
        super(name);
        this.configuration = configuration;
        compileResults = new CompileResults();
    }

    public CompileResults getCompileResults() {
        return compileResults;
    }

    public void ExecuteCommand(ProcessBuilder processBuilder, String command) throws IOException, InterruptedException {
        Process process;
        String temp;

        processBuilder.command("cmd.exe", "/c", command);
        processBuilder.directory(new File(System.getProperty("user.dir")));
        process = processBuilder.start();
        compileResults.exitCode = process.waitFor();
        while((temp = process.errorReader().readLine()) != null)
            compileResults.err.add(temp);
        while((temp = process.inputReader().readLine()) != null)
            compileResults.out.add(temp);
    }

    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        for (String command : configuration.commands) {
            try {
                ExecuteCommand(processBuilder, command.replace("{{name}}", getName()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
