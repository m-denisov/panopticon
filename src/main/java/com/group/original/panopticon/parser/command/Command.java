package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.Content;

public interface Command<C extends Content> {
   Content getContent();
   Command<C> setContent(C content);
}
