package com.group.original.panopticon.parser.command;

public enum Command {
    /**Main**/
    BREAK(Comment.EMPTY),
    EXIT(Comment.EMPTY),
    WAIT(Comment.EMPTY),
    REPORT(Comment.EMPTY),
    REPORT_AND_WAIT(Comment.EMPTY),
    INFO(Comment.EMPTY),
    COMMAND_INFO(Comment.EMPTY),

    /**Copier**/
    SWAP_ALL(BinaryContent.EMPTY),
    TRANSFER_NEW_FILES_BOTH_DIRECTIONS(BinaryContent.EMPTY),
    TRANSFER_NEW_FILES_DIRECT(BinaryContent.EMPTY),
    TRANSFER_NEW_FILES_REVERS(BinaryContent.EMPTY),
    SWAP_MODIFIED_FILES_BOTH_DIRECTIONS(BinaryContent.EMPTY),
    SWAP_MODIFIED_FILES_DIRECT(BinaryContent.EMPTY),
    SWAP_MODIFIED_FILES_REVERSE(BinaryContent.EMPTY),
    OPEN_COPY(BinaryContent.EMPTY),

    /**Researcher**/
    COMPARE(BinaryContent.EMPTY),
    COMPARE_LAZY(BinaryContent.EMPTY),
    GET_CHANGES(BinaryContent.EMPTY),
    MAKE_STAMP(BinaryContent.EMPTY)
    ;

    private CommandContent content;

    private Command(CommandContent content) {
        this.content = content;
    }

    public CommandContent getContent() {
        return content;
    }


}
