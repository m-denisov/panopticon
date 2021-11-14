package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.Content;
import com.group.original.panopticon.parser.command.content.CommentContent;

public class CommentCommand implements Command<CommentContent> {
    private CommentContent content;

    public CommentCommand(CommentContent content) {
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
    public Command setContent(CommentContent content) {
        this.content = content;
        return null;
    }
}
