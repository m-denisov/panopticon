package com.group.original.panopticon.parser;

public enum Commands {
    /**Main**/
    BREAK,
    EXIT,
    WAIT,
    REPORT,
    REPORT_AND_WAIT,
    INFO,
    COMMAND_INFO,

    /**Copier**/
    SWAP_ALL,
    TRANSFER_NEW_FILES_BOTH_DIRECTIONS,
    TRANSFER_NEW_FILES_DIRECT,
    TRANSFER_NEW_FILES_REVERS,
    SWAP_MODIFIED_FILES_BOTH_DIRECTIONS,
    SWAP_MODIFIED_FILES_DIRECT,
    SWAP_MODIFIED_FILES_REVERSE,
    OPEN_COPY,

    /**Researcher**/
    COMPARE,
    COMPARE_LAZY,
    GET_CHANGES,
    MAKE_STAMP,
    IS_SAVED


}
