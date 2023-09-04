package org.sawtooth.launcher.abstractions;

import org.sawtooth.configuration.models.LanguageConfiguration;
import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.models.LaunchResults;

public interface ILauncher {
    LaunchResults TryLaunch(LauncherConfiguration launcherConfiguration, String assembleName) throws InterruptedException;
}
