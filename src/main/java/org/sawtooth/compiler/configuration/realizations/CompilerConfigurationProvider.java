package org.sawtooth.compiler.configuration.realizations;

import org.sawtooth.compiler.configuration.CompilerConfiguration;
import org.sawtooth.compiler.configuration.abstractions.ICompilerConfigurationProvider;
import org.sawtooth.compiler.configuration.parser.abstractions.ICompilerConfigurationParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CompilerConfigurationProvider implements ICompilerConfigurationProvider {
    private final Map<String, CompilerConfiguration> compilerConfigurationMap;

    public CompilerConfigurationProvider(ICompilerConfigurationParser parser, String root) throws IOException {
        compilerConfigurationMap = new HashMap<String, CompilerConfiguration>();
        Initialization(parser, root);
    }

    private void Initialization(ICompilerConfigurationParser parser, String root) throws IOException {
        File[] files = (new File(root)).listFiles();
        CompilerConfiguration compilerConfiguration;
        IOException ioException = new IOException("Occurred error while reading configuration files.");

        if (files != null)
            try {
                for (File file : files) {
                    compilerConfiguration = parser.Parse(file.getPath());
                    compilerConfigurationMap.put(compilerConfiguration.langName, compilerConfiguration);
                }
            }
            catch (IOException e) {
                throw ioException;
            }
        else
            throw ioException;
    }

    public CompilerConfiguration TryGetValue(String langName) {
        return compilerConfigurationMap.get(langName);
    }
}
