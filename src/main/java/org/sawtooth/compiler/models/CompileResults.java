package org.sawtooth.compiler.models;

import java.util.ArrayList;

public class CompileResults {
    public int exitCode;
    public ArrayList<String> out;
    public ArrayList<String> err;

    public CompileResults() {
        out = new ArrayList<>();
        err = new ArrayList<>();
    }
}
