package org.sawtooth.launcher.abstractions;

import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.models.LaunchResults;

public interface ILauncher {
    LaunchResults TryLaunch(LauncherConfiguration configuration, String assembleName) throws InterruptedException;
}
