package com.group.original.panopticon.parser;

import com.group.original.panopticon.MainPaths;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class StandardPaths {
    private int pathsNumber;
    private Map<Integer, Path> paths;

    public StandardPaths() {

    }

    public void read() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document xml = documentBuilder.parse(MainPaths.STANDARD_PATHS_FILE.toString());
            System.out.println(xml.getElementById("mac").getTagName());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Path> getPaths() {
        return paths;
    }

    public Path get(int index) {
        return paths.get(index);
    }

    public void write(Path path) {

    }

    public void edit(int index, Path newValue) {

    }

    public void delete(int index) {

    }
}
