package org.sawtooth.launcher.models;

import java.util.ArrayList;

public class LaunchResults {
    public int exitCode;
    public ArrayList<String> out;
    public ArrayList<String> err;

    public LaunchResults() {
        out = new ArrayList<>();
        err = new ArrayList<>();
    }
}
