package com.group.original.panopticon.parser.command.content;

import java.nio.file.Path;

public class UnaryPathContent extends PathContent {
    public static final UnaryPathContent EMPTY = new UnaryPathContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };
    public Path path;

    public UnaryPathContent(Path path) {
        this.path = path;
    }
    private UnaryPathContent() {}

    public Path getFirst() {
        return path;
    }

    @Override
    public Path getSecond() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
