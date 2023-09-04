package org.sawtooth.launcher.realizations;

import org.sawtooth.launcher.abstractions.ILauncher;
import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.models.LaunchResults;

public class Launcher implements ILauncher {
    private final boolean isOnWindows;
    private final String sourcesPath;

    public Launcher(boolean isOnWindows, String sourcesPath) {
        this.isOnWindows = isOnWindows;
        this.sourcesPath = sourcesPath;
    }

    public LaunchResults TryLaunch(LauncherConfiguration launcherConfiguration, String assembleName) throws InterruptedException {
        LaunchThread launchThread = new LaunchThread(isOnWindows, assembleName, sourcesPath, launcherConfiguration);

        launchThread.start();
        synchronized(launchThread) {
            launchThread.wait();
        }
        return launchThread.getLaunchResults();
    }
}
