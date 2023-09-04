package org.sawtooth.launcher.realizations;

import java.io.File;
import java.io.IOException;

import org.sawtooth.configuration.models.LanguageConfiguration;
import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.models.LaunchResults;

public class LaunchThread extends Thread {
    private final LauncherConfiguration configuration;
    private final LaunchResults launchResults;
    private final boolean isOnWindows;
    private final String sourcesPath;

    public LaunchThread(boolean isOnWindows, String name, String sourcesPath, LauncherConfiguration configuration) {
        super(name);
        this.isOnWindows = isOnWindows;
        this.configuration = configuration;
        this.launchResults = new LaunchResults();
        this.sourcesPath = sourcesPath;
    }

    public LaunchResults getLaunchResults() {
        return this.launchResults;
    }

    private void WriteLaunchInput(Process process) throws IOException {
        for (String input : configuration.input) {
            process.outputWriter().write(input);
            process.outputWriter().newLine();
        }
        process.outputWriter().flush();
        process.outputWriter().close();
    }

    private void ReadLaunchErrors(Process process) throws IOException {
        String temp;

        while((temp = process.errorReader().readLine()) != null)
            this.launchResults.err.add(temp);
    }

    private void ReadLaunchOutput(Process process) throws IOException {
        String temp;

        while((temp = process.inputReader().readLine()) != null)
            this.launchResults.out.add(temp);
    }

    private void LaunchAssemble(ProcessBuilder processBuilder) throws IOException, InterruptedException {
        if (isOnWindows)
            processBuilder.command("cmd.exe", "/c", configuration.languageConfiguration.launchingCommand
                .replace("{{name}}", this.getName()).replace("{{path}}", sourcesPath));
        else
            processBuilder.command("sh", "-c", configuration.languageConfiguration.launchingCommand
                .replace("{{name}}", this.getName()).replace("{{path}}", sourcesPath));
        processBuilder.directory(new File(System.getProperty("user.dir")));
        Process process = processBuilder.start();

        WriteLaunchInput(process);
        this.launchResults.exitCode = process.waitFor();
        this.ReadLaunchErrors(process);
        this.ReadLaunchOutput(process);
    }

    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        try {
            this.LaunchAssemble(processBuilder);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
