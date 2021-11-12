package com.group.original.panopticon.parser.command;

import java.nio.file.Path;

public class BinaryContent extends CommandContent {
    public static final BinaryContent EMPTY = new BinaryContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    private BinaryContent() {}

    public BinaryContent(Path path) {
        this.path = path;
    }

    public Path path;

    public Path getPath() {
        return path;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
