package org.sawtooth.tester.realizations;

import org.sawtooth.launcher.configuration.models.LauncherConfiguration;
import org.sawtooth.launcher.realizations.LaunchThread;
import org.sawtooth.tester.abstractions.ITesterLauncher;
import org.sawtooth.tester.models.TestLaunchResults;

import java.util.Objects;

public class TesterLauncher implements ITesterLauncher {
    private final boolean isOnWindows;

    public TesterLauncher(boolean isOnWindows) {
        this.isOnWindows = isOnWindows;
    }

    private boolean CompareTestLaunchResults(TestLaunchResults testLaunchResults) {
        for (int i = 0; i < testLaunchResults.expected.size(); i++)
            if (!Objects.equals(testLaunchResults.launchResults.out.get(i), testLaunchResults.expected.get(i)))
                return false;
        return true;
    }

    public boolean TryComparedTestLaunch(LauncherConfiguration configuration, String assembleName) throws InterruptedException {
        TestLaunchResults testLaunchResults = TryTestLaunch(configuration, assembleName);

        if (testLaunchResults.launchResults.exitCode == 0)
            return CompareTestLaunchResults(testLaunchResults);
        return false;
    }

    public TestLaunchResults TryTestLaunch(LauncherConfiguration configuration, String assembleName) throws InterruptedException {
        LaunchThread launchThread = new LaunchThread(isOnWindows, assembleName, configuration);
        TestLaunchResults testLaunchResults = new TestLaunchResults();

        launchThread.start();
        synchronized(launchThread) {
            launchThread.wait();
        }
        testLaunchResults.launchResults = launchThread.getLaunchResults();
        testLaunchResults.expected = configuration.expected;
        return testLaunchResults;
    }
}
