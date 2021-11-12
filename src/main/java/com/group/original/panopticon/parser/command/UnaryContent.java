package com.group.original.panopticon.parser.command;

import java.nio.file.Path;

public class UnaryContent extends CommandContent {
    public static final UnaryContent EMPTY = new UnaryContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    private UnaryContent() {}

    public UnaryContent(Path path) {
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
