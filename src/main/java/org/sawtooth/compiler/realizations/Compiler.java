package org.sawtooth.compiler.realizations;

import org.sawtooth.compiler.abstractions.ICompiler;
import org.sawtooth.compiler.configuration.models.CompilerConfiguration;
import org.sawtooth.compiler.models.CompileResults;

public class Compiler implements ICompiler {
    private final boolean isOnWindows;

    public Compiler(boolean isOnWindows) {
        this.isOnWindows = isOnWindows;
    }

    public CompileResults TryCompile(CompilerConfiguration configuration, String assembleName, String sourcesPath) throws InterruptedException {
        CompileThread compileThread = new CompileThread(isOnWindows, assembleName, sourcesPath, configuration);
        compileThread.start();
        synchronized (compileThread) {
            compileThread.wait();
        }
        return compileThread.getCompileResults();
    }
}
