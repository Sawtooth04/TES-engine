package org.sawtooth.compiler.configuration.abstractions;

import org.sawtooth.compiler.configuration.models.CompilerConfiguration;

public interface ICompilerConfigurationProvider {
    public CompilerConfiguration TryGetValue(String name);
}
