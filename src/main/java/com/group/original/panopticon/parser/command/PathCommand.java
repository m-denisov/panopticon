package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.CommandContent;
import com.group.original.panopticon.parser.command.content.PathContent;

public class PathCommand implements Command {
    private PathContent pathContent;

    public PathCommand(PathContent pathContent) {
        this.pathContent = pathContent;
    }

    PathCommand() {
        pathContent = PathContent.EMPTY;
    }

    public void setContent(PathContent pathContent) {
        this.pathContent = pathContent;
    }

    @Override
    public PathContent getContent() {
        return pathContent;
    }
}
