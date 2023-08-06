package org.sawtooth.compiler.configuration.parser.realizations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sawtooth.compiler.configuration.CompilerConfiguration;
import org.sawtooth.compiler.configuration.parser.abstractions.ICompilerConfigurationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CompilerConfigurationParser implements ICompilerConfigurationParser {
    private Boolean IsFileExists(String path) {
        return (new File(path)).exists();
    }

    public CompilerConfiguration Parse(String path) throws IOException {
        if (!IsFileExists(path))
            throw new FileNotFoundException();
        else {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(path), CompilerConfiguration.class);
        }
    }
}
