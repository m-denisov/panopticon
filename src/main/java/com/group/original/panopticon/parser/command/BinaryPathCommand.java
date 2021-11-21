package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.BinaryPathContent;

public class BinaryPathCommand implements Command<BinaryPathContent> {
    private BinaryPathContent content;

    public BinaryPathCommand(BinaryPathContent content) {
        this.content = content;
    }

    BinaryPathCommand() {
        content = BinaryPathContent.EMPTY;
    }


    @Override
    public BinaryPathContent getContent() {
        return content;
    }

    @Override
    public BinaryPathCommand setContent(BinaryPathContent content) {
        this.content = content;
        return this;
    }
}
