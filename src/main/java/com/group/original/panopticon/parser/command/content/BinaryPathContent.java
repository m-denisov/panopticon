package com.group.original.panopticon.parser.command.content;

import java.nio.file.Path;

public class BinaryPathContent extends PathContent {
    public static final BinaryPathContent EMPTY = new BinaryPathContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    private BinaryPathContent() {}

    public BinaryPathContent(Path path) {
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
