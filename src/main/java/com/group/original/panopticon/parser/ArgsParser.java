package com.group.original.panopticon.parser;

import com.group.original.panopticon.parser.command.Command;
import com.group.original.panopticon.parser.command.CommentCommand;

import java.util.regex.Pattern;

public class ArgsParser {
    public static final String ANALYSE = "a ";
    public static final String COPY = "c ";
    public static final String MAKE_STAMP = "ms ";
    public static final String IS_SAVED = "s ";
    public static final String INFO = "info";
    public static final String EXIT = "exit";
    public static final String BREAK = "break";

    public static Command parse(String[] args) {
        if (args.length == 0) {

        }

        return null;
    }

    public static Command parse(String argsString) {

        String[] args = split(argsString);
        return parse(args);
    }

    private static String[] split(String argsString) {
        if (argsString.startsWith(ANALYSE)) {
            analyseParse(argsString);
        }

        return null;
    }

    private static void analyseParse(String argsString) {

    }


//    public static Command continueParsing(Command command, String[] args) {
//
//        return null;
//    }
}
