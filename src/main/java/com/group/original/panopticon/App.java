package com.group.original.panopticon;

import com.group.original.panopticon.io.InputManager;
import com.group.original.panopticon.io.OutputManager;
import com.group.original.panopticon.parser.ArgsParser;
import com.group.original.panopticon.parser.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App implements Flow.Subscriber<String> {
    private static final String MNEMONIC_NAME = "digit";
    private static final Pattern MNEMONIC_PATTERN = Pattern.compile("\"?(?<" + MNEMONIC_NAME + ">\\d)\"?");

    private InputManager inputManager;
    private OutputManager outputManager;
    private StandardPaths standardPaths;

    private Map<Integer, Path> mnemonics = new HashMap<>();

    public static void main(String[] args) {
        App app = new App();

    }

    public App() {
        //first receive command to show
//        readStandardPaths();
//        for (Map.Entry<Integer, Path> entry : mnemonics.entrySet()) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
//        inputManager = new ConsoleInputManager();
//        inputManager.subscribe(this);

        Pattern bi = Pattern.compile("(?<param>a|c)" +
                "(\\s(?<mod>[^p]*))?" +
                "\\s[pP][1]" + "\\s(?<p1>\".+\")" +
                "\\s[pP][2]" + "\\s(?<p2>\".+\")");
        Pattern simplePattern = Pattern.compile(
                "c\\s[a-z]*\\sp1\\s\".+\""
        );

        String simple = "c dir p1 \"/Users\"";

        String copy_mac_bi = "c dir n p1 \"/Users/westtochka/Documents/panopticonTest/first\" " +
                "p2 \"/Users/westtochka/Documents/panopticonTest/second\"";

        String ss = "c p \"/Users/westtochka/Documents/panopticonTest/first\"";

        Matcher matcher = bi.matcher(copy_mac_bi);
        Matcher matcher1 = ArgsParser.MAIN_BINARY.matcher(copy_mac_bi);
        Matcher matcherSimple = simplePattern.matcher(simple);
        if (matcher.find()) {
            System.out.println(matcher.group("param") + "|");
            System.out.println(matcher.group("mod") + "|");
//            System.out.println(simple.substring(matcherSimple.start(), matcherSimple.end()));
        }
    }


    public void readStandardPaths() {
        standardPaths = new StandardPaths();
        mnemonics = standardPaths.getStandardPaths();
    }

    public Path getPathThroughMnemonic(Path path) {
        if (Files.exists(path)) {
            return path;
        } else {
            Matcher matcher = MNEMONIC_PATTERN.matcher(path.toString());
            if (matcher.find()) {
                String mnemonic = matcher.group(MNEMONIC_NAME);
                int key = Integer.parseInt(mnemonic);
                Path target = mnemonics.get(key);
                if (target != null) {
                    return target;
                }
                throw new RuntimeException("invalid path");
            }
        }
        throw new RuntimeException("file not exist");
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(String item) {
        System.out.println(item);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
