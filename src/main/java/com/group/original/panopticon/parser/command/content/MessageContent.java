package com.group.original.panopticon.parser.command.content;

public class MessageContent implements Content {
    private String message;
    public static final MessageContent EMPTY = new MessageContent();

    public MessageContent(String message) {
        this.message = message;
    }

    private MessageContent() {
        message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
