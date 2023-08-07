package org.sawtooth;

import org.sawtooth.compiler.configuration.abstractions.ICompilerConfigurationProvider;
import org.sawtooth.compiler.configuration.parser.abstractions.ICompilerConfigurationParser;
import org.sawtooth.compiler.configuration.parser.realizations.CompilerConfigurationParser;
import org.sawtooth.compiler.configuration.realizations.CompilerConfigurationProvider;

public class Main {
    public static void main(String[] args) {
        try {
            ICompilerConfigurationParser compilerConfigurationParser = new CompilerConfigurationParser();
            ICompilerConfigurationProvider compilerConfigurationProvider = new CompilerConfigurationProvider(
                compilerConfigurationParser, "configurations");

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}