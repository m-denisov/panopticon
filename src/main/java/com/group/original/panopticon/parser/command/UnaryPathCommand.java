package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.PathContent;
import com.group.original.panopticon.parser.command.content.UnaryPathContent;

public class UnaryPathCommand extends PathCommand {
    public UnaryPathCommand(UnaryPathContent pathContent) {
        super(pathContent);
    }

    UnaryPathCommand() {
        super();
    }

    @Override
    public void setContent(PathContent pathContent) {
        if (pathContent instanceof UnaryPathContent) {
            super.setContent(pathContent);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public PathContent getContent() {
        return super.getContent();
    }
}
