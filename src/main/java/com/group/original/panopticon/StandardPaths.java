package com.group.original.panopticon;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class StandardPaths {
    private static final String OS_NAME_ATTR = "name";
    private static final String PATH_ID_ATTR = "id";

    private String osName;
    private Document xml;
    private XPath xPath;

    private int pathsNumber;
    private Map<Integer, Path> standardPath = new HashMap<>();

    public StandardPaths() {
        try {
            init();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        try {
            osName = readOsName();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        readStandardPaths();
    }

    private void init() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        xml = builder.parse(MainProgramPaths.STANDARD_PATHS_FILE.toString());
        XPathFactory xPathFactory = XPathFactory.newInstance();
        xPath = xPathFactory.newXPath();
    }

    private String readOsName() throws XPathExpressionException {
        XPathExpression osExpression = xPath.compile("/paths/os/@name");
        NodeList osList = (NodeList) osExpression.evaluate(xml, XPathConstants.NODESET);
        for (int i = 0; i < osList.getLength(); i++) {
            String os = osList.item(i).getNodeValue().toLowerCase();
            if (MainProgramPaths.OS_NAME.toLowerCase().contains(os)) {
                return os;
            }
        }
        throw new RuntimeException("no os name");
    }

    public void readStandardPaths() {
        try {
            List<Integer> idList = readIDs();

            for (int i = 1; i < idList.size() + 1; i++) {
                String expr = "paths/os[@name='" + osName + "']/path[@id=" + i + "]/text()";
                XPathExpression pathExpression = xPath.compile(expr);
                String stringPath = (String) pathExpression.evaluate(xml, XPathConstants.STRING);
                standardPath.put(i, Path.of(stringPath.trim()));
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> readIDs() throws XPathExpressionException {
        XPathExpression idExpression = xPath.compile("/paths/os[@name='" + osName + "']/path/@id");
        NodeList nodeList = (NodeList) idExpression.evaluate(xml, XPathConstants.NODESET);
        List<Integer> idList = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            idList.add(Integer.parseInt(nodeList.item(i).getNodeValue()));
        }
        return Collections.unmodifiableList(idList);
    }



    public Map<Integer, Path> getStandardPaths() {
        return standardPath;
    }

    public Path getStandardPathAtIndex(int index) {
        return standardPath.get(index);
    }

    public void writeStandardPath(Path path) {

    }

    public void editStandardPath(int index, Path newValue) {

    }

    public void deleteStandardPath(int index) {

    }
}
