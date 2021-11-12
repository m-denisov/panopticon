package com.group.original.panopticon.parser.command;

public class Commands {
    /**Main**/
    public static final Command BREAK = new CommentCommand();
    public static final Command EXIT = new CommentCommand();
//    public static final Command WAIT = new CommentCommand();
    public static final Command REPORT = new CommentCommand();
    public static final Command REPORT_AND_WAIT = new CommentCommand();
    public static final Command INFO = new CommentCommand();
    public static final Command COMMAND_INFO = new CommentCommand();

    /**Copier**/
    public static final Command SWAP_ALL = new PathCommand();
    public static final Command TRANSFER_NEW_FILES_BOTH_DIRECTIONS = new PathCommand();
    public static final Command TRANSFER_NEW_FILES_DIRECT = new PathCommand();
    public static final Command TRANSFER_NEW_FILES_REVERS = new PathCommand();
    public static final Command SWAP_MODIFIED_FILES_BOTH_DIRECTIONS = new PathCommand();
    public static final Command SWAP_MODIFIED_FILES_DIRECT = new PathCommand();
    public static final Command SWAP_MODIFIED_FILES_REVERSE = new PathCommand();
    public static final Command OPEN_COPY = new UnaryPathCommand();

    /**Researcher**/
//    public static final Command COMPARE = new PathCommand();
    public static final Command COMPARE_LAZY = new PathCommand();
    public static final Command GET_CHANGES = new UnaryPathCommand();
    public static final Command MAKE_STAMP = new UnaryPathCommand();
    public static final Command IS_SAVE = new UnaryPathCommand();
}
