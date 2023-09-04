package org.sawtooth.configuration.parser.abstractions;

import org.sawtooth.configuration.models.LanguageConfiguration;

import java.io.IOException;

public interface ILanguageConfigurationParser {
    public LanguageConfiguration Parse(String path) throws IOException;
}
