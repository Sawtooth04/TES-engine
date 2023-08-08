package org.sawtooth.launcher.configuration.parser.abstractions;

import org.sawtooth.launcher.configuration.models.LauncherConfiguration;

import java.io.IOException;

public interface ILauncherConfigurationParser {
    public LauncherConfiguration Parse(String path) throws IOException;
}
