package org.sawtooth.compiler.configuration.parser.abstractions;

import org.sawtooth.compiler.configuration.CompilerConfiguration;

import java.io.IOException;

public interface ICompilerConfigurationParser {
    public CompilerConfiguration Parse(String path) throws IOException;
}
