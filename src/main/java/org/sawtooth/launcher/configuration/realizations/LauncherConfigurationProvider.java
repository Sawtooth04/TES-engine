package org.sawtooth.launcher.configuration.realizations;

import org.sawtooth.compiler.configuration.models.CompilerConfiguration;
import org.sawtooth.compiler.configuration.parser.abstractions.ICompilerConfigurationParser;
import org.sawtooth.launcher.configuration.abstractions.ILauncherConfigurationProvider;
import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.configuration.parser.abstractions.ILauncherConfigurationParser;
import org.sawtooth.launcher.configuration.parser.realizations.LauncherConfigurationParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LauncherConfigurationProvider implements ILauncherConfigurationProvider {
    private final ILauncherConfigurationParser parser;

    public LauncherConfigurationProvider() {
        parser = new LauncherConfigurationParser();
    }

    public ArrayList<LauncherConfiguration> TryGetLauncherConfigurations(String path) throws IOException {
        File[] files = (new File(path)).listFiles();
        ArrayList<LauncherConfiguration> result = new ArrayList<>();
        IOException ioException = new IOException("Occurred error while reading launcher configuration files.");

        if (files != null)
            try {
                for (File file : files)
                    result.add(parser.Parse(file.getPath()));
            }
            catch (IOException e) {
                throw ioException;
            }
        else
            throw ioException;
        return result;
    }
}
