package org.sawtooth;

import org.sawtooth.compiler.abstractions.ICompiler;
import org.sawtooth.compiler.configuration.abstractions.ICompilerConfigurationProvider;
import org.sawtooth.compiler.configuration.parser.abstractions.ICompilerConfigurationParser;
import org.sawtooth.compiler.configuration.parser.realizations.CompilerConfigurationParser;
import org.sawtooth.compiler.configuration.realizations.CompilerConfigurationProvider;
import org.sawtooth.compiler.models.CompileResults;
import org.sawtooth.compiler.realizations.Compiler;
import org.sawtooth.launcher.abstractions.ILauncher;
import org.sawtooth.launcher.configuration.abstractions.ILauncherConfigurationProvider;
import org.sawtooth.launcher.configuration.realizations.LauncherConfigurationProvider;
import org.sawtooth.launcher.models.LaunchResults;
import org.sawtooth.launcher.realizations.Launcher;

public class Main {
    public static void main(String[] args) {
        try {
            ICompilerConfigurationParser compilerConfigurationParser = new CompilerConfigurationParser();
            ICompilerConfigurationProvider compilerConfigurationProvider = new CompilerConfigurationProvider(
                compilerConfigurationParser, "configurations");
            ICompiler compiler = new Compiler();
            ILauncherConfigurationProvider launcherConfigurationProvider = new LauncherConfigurationProvider();
            ILauncher launcher = new Launcher();

            //example
            CompileResults compileResults = compiler.TryCompile(compilerConfigurationProvider.TryGetValue("java"), "test");
            LaunchResults launchResults = launcher.TryLaunch(
                launcherConfigurationProvider.TryGetLauncherConfigurations("launchConfigurations/test").get(0),
                "test"
            );
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}