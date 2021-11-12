package com.group.original.panopticon.parser.command;

import java.nio.file.Path;

public class Comment extends CommandContent {
    public static final Comment EMPTY = new Comment() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    private Comment() {}

    public Comment(String comment) {
        this.comment = comment;
    }

    public String comment;

    public String getComment() {
        return comment;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
