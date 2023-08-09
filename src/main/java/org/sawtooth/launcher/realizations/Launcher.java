package org.sawtooth.launcher.realizations;

import org.sawtooth.launcher.abstractions.ILauncher;
import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.models.LaunchResults;

public class Launcher implements ILauncher {
    public LaunchResults TryLaunch(LauncherConfiguration configuration, String assembleName) throws InterruptedException {
        LaunchThread launchThread = new LaunchThread(assembleName, configuration);

        launchThread.start();
        synchronized(launchThread) {
            launchThread.wait();
        }
        return launchThread.getLaunchResults();
    }
}
