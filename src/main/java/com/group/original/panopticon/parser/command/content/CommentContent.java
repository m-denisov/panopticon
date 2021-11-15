package com.group.original.panopticon.parser.command.content;

public class CommentContent implements Content {
    private String comment;
    public static final CommentContent EMPTY = new CommentContent();

    public CommentContent(String comment) {
        this.comment = comment;
    }

    private CommentContent() {
        comment = "";
    }


    @Override
    public boolean isEmpty() {
        return false;
    }
}
