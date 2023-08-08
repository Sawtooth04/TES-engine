package org.sawtooth.compiler.realizations;

import org.sawtooth.compiler.abstractions.ICompiler;
import org.sawtooth.compiler.configuration.CompilerConfiguration;

public class Compiler implements ICompiler {
    public CompileResults TryCompile(CompilerConfiguration configuration, String assembleName) throws InterruptedException {
        CompileThread compileThread = new CompileThread(assembleName, configuration);
        compileThread.start();
        synchronized (compileThread) {
            compileThread.wait();
        }
        return compileThread.getCompileResults();
    }
}
