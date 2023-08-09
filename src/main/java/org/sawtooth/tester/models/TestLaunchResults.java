package org.sawtooth.tester.models;

import org.sawtooth.launcher.models.LaunchResults;

import java.util.ArrayList;

public class TestLaunchResults {
    public LaunchResults launchResults;
    public ArrayList<String> expected;

    public TestLaunchResults() {
        launchResults = new LaunchResults();
        expected = new ArrayList<>();
    }
}
