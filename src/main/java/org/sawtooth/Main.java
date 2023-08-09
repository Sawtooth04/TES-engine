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
import org.sawtooth.launcher.realizations.Launcher;
import org.sawtooth.tester.abstractions.ITesterLauncher;
import org.sawtooth.tester.realizations.TesterLauncher;

public class Main {
    public static void main(String[] args) {
        try {
            ICompilerConfigurationParser compilerConfigurationParser = new CompilerConfigurationParser();
            ICompilerConfigurationProvider compilerConfigurationProvider = new CompilerConfigurationProvider(
                compilerConfigurationParser, "configurations");
            ICompiler compiler = new Compiler();
            ILauncherConfigurationProvider launcherConfigurationProvider = new LauncherConfigurationProvider();
            ILauncher launcher = new Launcher();
            ITesterLauncher testerLauncher = new TesterLauncher();

            //example
            CompileResults compileResults = compiler.TryCompile(compilerConfigurationProvider.TryGetValue("python"), "test_py");
            boolean launchResults = testerLauncher.TryComparedTestLaunch(
                launcherConfigurationProvider.TryGetLauncherConfigurations("launchConfigurations/test_py").get(0),
                "test_py"
            );
            System.out.println(launchResults);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}