package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.CommentContent;
import com.group.original.panopticon.parser.command.content.Content;

public class CommentCommand implements Command<Content> {
    private Content content;

    public CommentCommand(Content content) {
        this.content = content;
    }

    CommentCommand() {
        content = CommentContent.EMPTY;
    }

    @Override
    public Content getContent() {
        return content;
    }

    @Override
    public Command setContent(Content content) {
        this.content = content;
        return null;
    }
}
