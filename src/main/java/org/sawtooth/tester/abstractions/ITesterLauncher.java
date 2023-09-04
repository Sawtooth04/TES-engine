package org.sawtooth.tester.abstractions;

import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.tester.models.TestLaunchResults;

public interface ITesterLauncher {
    public boolean TryComparedTestLaunch(LauncherConfiguration launcherConfiguration, String assembleName) throws InterruptedException;

    public TestLaunchResults TryTestLaunch(LauncherConfiguration launcherConfiguration, String assembleName) throws InterruptedException;
}
