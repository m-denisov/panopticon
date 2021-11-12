package com.group.original.panopticon.parser.command.content;

public class CommentContent implements CommandContent {
    public static final CommentContent EMPTY = new CommentContent() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    private CommentContent() {}

    public CommentContent(String comment) {
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
