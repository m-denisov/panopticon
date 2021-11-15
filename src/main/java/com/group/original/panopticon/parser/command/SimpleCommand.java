package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.Content;

public class SimpleCommand implements Command<Content> {
    private Content content = new Content() {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    @Override
    public Content getContent() {
        return content;
    }

    @Override
    public Command<Content> setContent(Content content) {
        throw new RuntimeException();
    }
}
