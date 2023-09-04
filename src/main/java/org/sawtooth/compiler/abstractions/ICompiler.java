package org.sawtooth.compiler.abstractions;

import org.sawtooth.compiler.models.CompileResults;
import org.sawtooth.configuration.models.LanguageConfiguration;

public interface ICompiler {
    public CompileResults TryCompile(LanguageConfiguration configuration, String assembleName, String sourcesPath)
        throws InterruptedException;
}