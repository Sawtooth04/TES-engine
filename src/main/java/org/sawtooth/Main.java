package org.sawtooth;

import org.sawtooth.compiler.abstractions.ICompiler;
import org.sawtooth.compiler.configuration.abstractions.ICompilerConfigurationProvider;
import org.sawtooth.compiler.configuration.parser.abstractions.ICompilerConfigurationParser;
import org.sawtooth.compiler.configuration.parser.realizations.CompilerConfigurationParser;
import org.sawtooth.compiler.configuration.realizations.CompilerConfigurationProvider;
import org.sawtooth.compiler.realizations.CompileResults;
import org.sawtooth.compiler.realizations.Compiler;

public class Main {
    public static void main(String[] args) {
        try {
            ICompilerConfigurationParser compilerConfigurationParser = new CompilerConfigurationParser();
            ICompilerConfigurationProvider compilerConfigurationProvider = new CompilerConfigurationProvider(
                compilerConfigurationParser, EngineConfiguration.configurationsPath);
            ICompiler compiler = new Compiler();

            CompileResults results = compiler.TryCompile(compilerConfigurationProvider.TryGetValue("java"), "test");
            System.out.println(results.exitCode);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}