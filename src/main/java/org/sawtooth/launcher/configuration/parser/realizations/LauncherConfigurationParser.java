package org.sawtooth.launcher.configuration.parser.realizations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sawtooth.compiler.configuration.models.CompilerConfiguration;
import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.configuration.parser.abstractions.ILauncherConfigurationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LauncherConfigurationParser implements ILauncherConfigurationParser {

    private Boolean IsFileExists(String path) {
        return (new File(path)).exists();
    }

    public LauncherConfiguration Parse(String path) throws IOException {
        if (!IsFileExists(path))
            throw new FileNotFoundException();
        else {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(path), LauncherConfiguration.class);
        }
    }
}
