package com.group.original.panopticon;

import com.group.original.panopticon.parser.command.Command;
import com.group.original.panopticon.parser.command.UnaryPathCommand;
import com.group.original.panopticon.parser.command.content.UnaryPathContent;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class App {

    private static Map<Integer, Path> mnemonics = new HashMap<>();

    public static void main(String[] args) {
        Path wsecondStampPath = Path.of("C:\\Users\\m.denisov\\stamps\\2.txt");

        Path wfirst = Path.of("C:\\Users\\m.denisov\\Documents\\Оборудование");
        Path wsecond = Path.of("D:\\test");
        Path wtestFile = Path.of("C:\\Users\\m.denisov\\Documents\\временные\\Без имени 1.odt");
        Path wthird = Path.of("F:\\1EN-TraceWay\\2.9. Департамент качества\\Проекты");

        Path mfirst = Path.of("/Users/westtochka/Documents/panopticonTest/first");
        Path msecond = Path.of("/Users/westtochka/Documents/panopticonTest/second");


//        Researcher researcher = new Researcher();
//        Copier copier = new Copier();
//        Differences differences = researcher.compare(mfirst, msecond);
//        copier.transferNewFilesOneDirection(differences, TransferOrder.REVERS);


//        String p = MainPaths.class.getClassLoader().getResource("StandardPaths.xml").getPath();
//        System.out.println("-----");
//        System.out.println(p);
//        System.out.println("-----");

//        String s = "";
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            s = reader.readLine();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//        String s1 = "a p \"/Users/westtochka/Documents/panopticonTest/first\"";
//        String s2 = "a p \"C:\\Users\\m.denisov\\Documents\\Оборудование\"";
//        ArgsParser.parse(s);

        Command command = new UnaryPathCommand(new UnaryPathContent(wfirst));
    }

}
