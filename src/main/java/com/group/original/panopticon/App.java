package com.group.original.panopticon;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private static final String MNEMONIC_NAME = "digit";
    private static final Pattern MNEMONIC_PATTERN = Pattern.compile("\"?(?<" + MNEMONIC_NAME + ">\\d)\"?");

    private StandardPaths standardPaths;

    private Map<Integer, Path> mnemonics = new HashMap<>();

    public static void main(String[] args) {
        App app = new App();

    }

    public App() {
        //first receive command to show
        standardPaths = new StandardPaths();

    }

    public Path getPath(Path path) {
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

}
