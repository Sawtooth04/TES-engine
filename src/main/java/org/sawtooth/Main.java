package org.sawtooth;

import org.sawtooth.compiler.abstractions.ICompiler;
import org.sawtooth.compiler.models.CompileResults;
import org.sawtooth.compiler.realizations.Compiler;
import org.sawtooth.configuration.abstractions.ILanguageConfigurationProvider;
import org.sawtooth.configuration.parser.abstractions.ILanguageConfigurationParser;
import org.sawtooth.configuration.parser.realizations.LanguageConfigurationParser;
import org.sawtooth.configuration.realizations.LanguageConfigurationProvider;
import org.sawtooth.launcher.abstractions.ILauncher;
import org.sawtooth.launcher.configuration.abstractions.ILauncherConfigurationProvider;
import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.configuration.realizations.LauncherConfigurationProvider;
import org.sawtooth.launcher.realizations.Launcher;
import org.sawtooth.tester.abstractions.ITesterLauncher;
import org.sawtooth.tester.realizations.TesterLauncher;

public class Main {
    public static void main(String[] args) {
        try {
            boolean isOnWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
            ILanguageConfigurationParser languageConfigurationParser = new LanguageConfigurationParser();
            ILanguageConfigurationProvider languageConfigurationProvider = new LanguageConfigurationProvider(
                languageConfigurationParser, "configurations");
            ICompiler compiler = new Compiler(isOnWindows);
            ILauncherConfigurationProvider launcherConfigurationProvider = new LauncherConfigurationProvider();
            ILauncher launcher = new Launcher(isOnWindows, "sources");
            ITesterLauncher testerLauncher = new TesterLauncher(isOnWindows, "sources");

            //example
            CompileResults compileResults = compiler.TryCompile(languageConfigurationProvider.TryGetValue("python"),
                "test_py", "sources");

            LauncherConfiguration launcherConfiguration = launcherConfigurationProvider.TryGetLauncherConfigurations("launchConfigurations/test_py").get(0);
            launcherConfiguration.languageConfiguration = languageConfigurationProvider.TryGetValue("python");

            boolean launchResults = testerLauncher.TryComparedTestLaunch(launcherConfiguration, "test_py");
            System.out.println(launchResults);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}