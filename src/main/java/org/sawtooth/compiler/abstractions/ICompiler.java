package org.sawtooth.compiler.abstractions;

import org.sawtooth.compiler.configuration.CompilerConfiguration;
import org.sawtooth.compiler.realizations.CompileResults;

public interface ICompiler {
    public CompileResults TryCompile(CompilerConfiguration configuration, String assembleName) throws InterruptedException;
}