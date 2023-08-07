package org.sawtooth.compiler.configuration.abstractions;

import org.sawtooth.compiler.configuration.CompilerConfiguration;

public interface ICompilerConfigurationProvider {
    public CompilerConfiguration TryGetValue(String langName);
}
