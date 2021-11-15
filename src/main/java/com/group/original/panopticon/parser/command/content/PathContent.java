package com.group.original.panopticon.parser.command.content;

import java.nio.file.Path;

public abstract class PathContent implements Content {
    public static final PathContent EMPTY = new PathContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        Path getFirst() {
            return null;
        }

        @Override
        Path getSecond() {
            return null;
        }
    };

    abstract Path getFirst();
    abstract Path getSecond();
}
