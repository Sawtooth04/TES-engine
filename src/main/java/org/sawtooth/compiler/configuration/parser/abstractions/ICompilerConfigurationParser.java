package org.sawtooth.compiler.configuration.parser.abstractions;

import org.sawtooth.compiler.configuration.models.CompilerConfiguration;

import java.io.IOException;

public interface ICompilerConfigurationParser {
    public CompilerConfiguration Parse(String path) throws IOException;
}
