package com.group.original.panopticon.parser.command;

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
    public UnaryPathContent getContent() {
        return content;
    }

    @Override
    public UnaryPathCommand setContent(UnaryPathContent content) {
        this.content = content;
        return this;
    }
}
