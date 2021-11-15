package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.Content;
import com.group.original.panopticon.parser.command.content.UnaryPathContent;

public class UnaryPathCommand implements Command<UnaryPathContent> {
    private UnaryPathContent content;

    public UnaryPathCommand(UnaryPathContent content) {
        this.content = content;
    }

    UnaryPathCommand() {
        content = UnaryPathContent.EMPTY;
    }

    @Override
    public Content getContent() {
        return content;
    }

    @Override
    public Command setContent(UnaryPathContent content) {
        this.content = content;
        return this;
    }
}
