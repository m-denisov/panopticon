package com.group.original.panopticon;

import java.nio.file.Path;
import java.util.Arrays;

public class App {

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

        System.out.println(Arrays.toString(args));
    }

}
