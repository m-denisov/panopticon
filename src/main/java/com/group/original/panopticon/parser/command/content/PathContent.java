package com.group.original.panopticon.parser.command.content;

public abstract class PathContent implements CommandContent {
    public static final PathContent EMPTY = new PathContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };
}
