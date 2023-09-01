package org.sawtooth.compiler.abstractions;

import org.sawtooth.compiler.configuration.models.CompilerConfiguration;
import org.sawtooth.compiler.models.CompileResults;

public interface ICompiler {
    public CompileResults TryCompile(CompilerConfiguration configuration, String assembleName, String sourcesPath)
        throws InterruptedException;
}