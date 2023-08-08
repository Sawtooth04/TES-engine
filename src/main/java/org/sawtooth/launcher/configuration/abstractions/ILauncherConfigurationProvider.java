package org.sawtooth.launcher.configuration.abstractions;

import org.sawtooth.launcher.configuration.models.LauncherConfiguration;

import java.io.IOException;
import java.util.ArrayList;

public interface ILauncherConfigurationProvider {
    public ArrayList<LauncherConfiguration> TryGetLauncherConfigurations(String path) throws IOException;
}
