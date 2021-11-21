package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.PathContent;

public class PathCommand implements Command<PathContent> {
    private PathContent pathContent;

    public PathCommand(PathContent pathContent) {
        this.pathContent = pathContent;
    }

    PathCommand() {
        pathContent = PathContent.EMPTY;
    }

    @Override
    public PathContent getContent() {
        return pathContent;
    }

    @Override
    public PathCommand setContent(PathContent content) {
        this.pathContent = content;
        return this;
    }
}
