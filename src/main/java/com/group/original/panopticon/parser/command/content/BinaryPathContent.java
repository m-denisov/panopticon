package com.group.original.panopticon.parser.command.content;

import java.nio.file.Path;

public class BinaryPathContent extends PathContent {
    public static final BinaryPathContent EMPTY = new BinaryPathContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };
    private Path firstPath;
    private Path secondPath;

    public BinaryPathContent(Path firstPath, Path secondPath) {
        this.firstPath = firstPath;
        this.secondPath = secondPath;
    }
    private BinaryPathContent() {}

    public Path getFirst() {
        return firstPath;
    }

    public Path getSecond() {
        return secondPath;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
