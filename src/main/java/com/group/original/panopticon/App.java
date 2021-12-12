package com.group.original.panopticon;

import com.group.original.panopticon.io.ConsoleInputManager;
import com.group.original.panopticon.io.InputManager;
import com.group.original.panopticon.io.OutputManager;

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
        inputManager = new ConsoleInputManager();
        inputManager.subscribe(this);
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
