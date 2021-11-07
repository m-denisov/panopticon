package com.group.original.panopticon.parser;

import com.group.original.panopticon.MainPaths;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class StandardPaths {
    private int pathsNumber;
    private Map<Integer, Path> paths;

    public StandardPaths() {

    }

    public void read() {

    }

    public Map<Integer, Path> getPaths() {
        return paths;
    }

    public Path get(int index) {
        return paths.get(index);
    }

    public void write(Path path) {

    }

    public void edit(int index, Path newValue) {

    }

    public void delete(int index) {

    }
}
