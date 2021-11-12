package com.group.original.panopticon.parser.command.content;

import java.nio.file.Path;

public class UnaryPathContent extends PathContent {
    public static final UnaryPathContent EMPTY = new UnaryPathContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    private UnaryPathContent() {}

    public UnaryPathContent(Path path) {
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
