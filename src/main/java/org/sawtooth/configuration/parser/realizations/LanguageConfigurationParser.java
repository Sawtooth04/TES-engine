package org.sawtooth.configuration.parser.realizations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sawtooth.compiler.configuration.models.CompilerConfiguration;
import org.sawtooth.configuration.models.LanguageConfiguration;
import org.sawtooth.configuration.parser.abstractions.ILanguageConfigurationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LanguageConfigurationParser implements ILanguageConfigurationParser {
    private Boolean IsFileExists(String path) {
        return (new File(path)).exists();
    }

    public LanguageConfiguration Parse(String path) throws IOException {
        if (!IsFileExists(path))
            throw new FileNotFoundException();
        else {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(path), LanguageConfiguration.class);
        }
    }
}
