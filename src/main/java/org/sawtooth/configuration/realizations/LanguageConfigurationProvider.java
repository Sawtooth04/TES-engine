package org.sawtooth.configuration.realizations;

import org.sawtooth.configuration.abstractions.ILanguageConfigurationProvider;
import org.sawtooth.configuration.models.LanguageConfiguration;
import org.sawtooth.configuration.parser.abstractions.ILanguageConfigurationParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LanguageConfigurationProvider implements ILanguageConfigurationProvider {
    private final Map<String, LanguageConfiguration> languageConfigurationMap;

    public LanguageConfigurationProvider(ILanguageConfigurationParser parser, String root) throws IOException {
        languageConfigurationMap = new HashMap<String, LanguageConfiguration>();
        Initialization(parser, root);
    }

    private void Initialization(ILanguageConfigurationParser parser, String root) throws IOException {
        File[] files = (new File(root)).listFiles();
        LanguageConfiguration languageConfiguration;
        IOException ioException = new IOException("Occurred error while reading configuration files.");

        if (files != null)
            try {
                for (File file : files) {
                    languageConfiguration = parser.Parse(file.getPath());
                    languageConfigurationMap.put(languageConfiguration.name, languageConfiguration);
                }
            }
            catch (IOException e) {
                throw ioException;
            }
        else
            throw ioException;
    }

    public synchronized LanguageConfiguration TryGetValue(String name) {
        return languageConfigurationMap.get(name);
    }
}
