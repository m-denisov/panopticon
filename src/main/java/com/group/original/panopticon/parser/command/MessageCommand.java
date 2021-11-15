package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.MessageContent;
import com.group.original.panopticon.parser.command.content.Content;

public class MessageCommand implements Command<Content> {
    private Content content;

    public MessageCommand(Content content) {
        this.content = content;
    }

    MessageCommand() {
        content = MessageContent.EMPTY;
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
