package com.group.original.panopticon.parser;

import com.group.original.panopticon.parser.command.Command;
import com.group.original.panopticon.parser.command.Commands;
import com.group.original.panopticon.parser.command.content.BinaryPathContent;
import com.group.original.panopticon.parser.command.content.Content;
import com.group.original.panopticon.parser.command.content.PathContent;
import com.group.original.panopticon.parser.command.content.UnaryPathContent;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsParser {
    public static final String PARAMETER = "parameter";
    public static final String MODIFIER = "modifier";
    public static final String PATH = "path";
    public static final String PATH_1 = "path1";
    public static final String PATH_2 = "path2";

    public static final Pattern MAIN_UNARY = Pattern.compile("(?<" + PARAMETER + ">" +
            Parameters.ANALYSE + "|" +
            Parameters.COPY + "|" +
            Parameters.MAKE_STAMP + "|" +
            Parameters.IS_SAVED + ")" +
            "(\\s(?<" + MODIFIER + ">[a-zA-Z&&[^pP]]))?" +
            "\\s[pP]" +
            "\\s(?<" + PATH + ">\".+\")");

    public static final Pattern MAIN_BINARY = Pattern.compile("(?<" + PARAMETER + ">" +
            Parameters.ANALYSE + "|" +
            Parameters.COPY + ")" +
            "(\\s(?<" + MODIFIER + ">[a-zA-Z&&[^pP]]))?" +
            "\\s[pP][1]" +
            "\\s(?<" + PATH_1 + ">\".+\")" +
            "\\s[pP][2]" +
            "\\s(?<" + PATH_2 + ">\".+\")");

    public static Command parse(String[] args) {
        if (args.length == 0) {

        }

        return null;
    }

    public static Command<? extends Content> parse(String argsString) {
        if (argsString.equals(Parameters.EXIT)) return Commands.EXIT;
        if (argsString.equals(Parameters.BREAK)) return Commands.BREAK;
        if (argsString.equals(Parameters.INFO)) return Commands.INFO;

        Matcher unaryMatcher = MAIN_UNARY.matcher(argsString);
        Matcher binaryMatcher = MAIN_BINARY.matcher(argsString);
        if (unaryMatcher.find()) {
            UnaryPathContent unaryPathContent = new UnaryPathContent(
                    Path.of(unaryMatcher.group(PATH)));
            return parseUnary(unaryMatcher, unaryPathContent);
        } else if (binaryMatcher.find()) {
            BinaryPathContent binaryPathContent = new BinaryPathContent(
                    Path.of(binaryMatcher.group(PATH_1)),
                    Path.of(binaryMatcher.group(PATH_2)));
            return parseBinary(binaryMatcher, binaryPathContent);
        }
        throw new RuntimeException(Commands.REPORT.toString());
    }

    private static Command<? extends PathContent> parseUnary(Matcher unaryMatcher, UnaryPathContent content) {
        switch (unaryMatcher.group(PARAMETER)) {
            case Parameters.ANALYSE:
                return selectAnalyseModifiers(unaryMatcher, content);
            case Parameters.COPY:
                return selectCopyModifiers(unaryMatcher, content);
            case Parameters.MAKE_STAMP:
                return selectMakeStampModifiers(unaryMatcher, content);
            case Parameters.IS_SAVED:
                return Commands.IS_SAVE.setContent(content);
        }
        throw new RuntimeException();
    }

    private static Command<? extends PathContent> parseBinary(Matcher binaryMatcher, BinaryPathContent content) {
        switch (binaryMatcher.group(PARAMETER)) {
            case Parameters.ANALYSE:
                return selectAnalyseModifiers(binaryMatcher, content);
            case Parameters.COPY:
                return selectCopyModifiers(binaryMatcher, content);
        }
        throw new RuntimeException();
    }

    private static Command<? extends PathContent> selectAnalyseModifiers(Matcher matcher, PathContent content) {
        return Commands.COMPARE_LAZY.setContent(content);
    }

    private static Command<? extends PathContent> selectCopyModifiers(Matcher matcher, PathContent content) {
        String mod = matcher.group(MODIFIER);

        if (mod == null || mod.isBlank()) return Commands.SWAP_ALL.setContent(content);

        switch (mod) {
            case Modifiers.MODIFIED:
                return Commands.SWAP_MODIFIED_FILES_DIRECT.setContent(content);
            case Modifiers.MODIFIED_ALL:
                return Commands.SWAP_MODIFIED_FILES_BOTH_DIRECTIONS.setContent(content);
            case Modifiers.NEW:
                return Commands.TRANSFER_NEW_FILES_DIRECT.setContent(content);
            case Modifiers.NEW_ALL:
                return Commands.TRANSFER_NEW_FILES_BOTH_DIRECTIONS.setContent(content);
        }
        throw new RuntimeException(Commands.REPORT.toString());
    }

    private static Command<UnaryPathContent> selectMakeStampModifiers(Matcher matcher, UnaryPathContent content) {
        return Commands.MAKE_STAMP.setContent(content);
    }

//    public static Command continueParsing(Command command, String[] args) {
//
//        return null;
//    }
}
