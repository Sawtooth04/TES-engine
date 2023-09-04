package org.sawtooth.configuration.abstractions;

import org.sawtooth.configuration.models.LanguageConfiguration;

public interface ILanguageConfigurationProvider {
    public LanguageConfiguration TryGetValue(String name);
}
