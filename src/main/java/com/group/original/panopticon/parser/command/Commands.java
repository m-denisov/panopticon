package com.group.original.panopticon.parser.command;

import com.group.original.panopticon.parser.command.content.Content;
import com.group.original.panopticon.parser.command.content.PathContent;
import com.group.original.panopticon.parser.command.content.UnaryPathContent;

public class Commands {
    /**Main**/
    public static final Command<Content> BREAK = new SimpleCommand();
    public static final Command<Content> EXIT = new SimpleCommand();
    public static final Command<Content> EXIT_NOW = new SimpleCommand();
//    public static final Command WAIT = new CommentCommand();
    public static final Command<Content> REPORT = new MessageCommand();
    public static final Command<Content> REPORT_AND_WAIT = new MessageCommand();
    public static final Command<Content> INFO = new MessageCommand();
    public static final Command<Content> COMMAND_INFO = new MessageCommand();

    /**Copier**/
    public static final Command<PathContent> SWAP_ALL = new PathCommand();
    public static final Command<PathContent> TRANSFER_NEW_FILES_BOTH_DIRECTIONS = new PathCommand();
    public static final Command<PathContent> TRANSFER_NEW_FILES_DIRECT = new PathCommand();
    public static final Command<PathContent> TRANSFER_NEW_FILES_REVERS = new PathCommand();
    public static final Command<PathContent> SWAP_MODIFIED_FILES_BOTH_DIRECTIONS = new PathCommand();
    public static final Command<PathContent> SWAP_MODIFIED_FILES_DIRECT = new PathCommand();
    public static final Command<PathContent> SWAP_MODIFIED_FILES_REVERSE = new PathCommand();
//    public static final Command OPEN_COPY = new UnaryPathCommand();

    /**Researcher**/
//    public static final Command COMPARE = new PathCommand();
    public static final Command<PathContent> COMPARE_LAZY = new PathCommand();
//    public static final Command<UnaryPathContent> GET_CHANGES = new UnaryPathCommand();
    public static final Command<UnaryPathContent> MAKE_STAMP = new UnaryPathCommand();
    public static final Command<UnaryPathContent> IS_SAVE = new UnaryPathCommand();
}
