package com.group.original.panopticon;

import com.group.original.panopticon.exception.ExceptionHandler;
import com.group.original.panopticon.file.Copier;
import com.group.original.panopticon.file.Researcher;
import com.group.original.panopticon.file.differences.Differences;
import com.group.original.panopticon.file.system.DirectoryStamp;
import com.group.original.panopticon.file.system.Stamper;
import com.group.original.panopticon.output.manager.ConsoleOutputManager;

import java.nio.file.Path;
import java.util.*;

public class App {

    public static void main(String[] args) {
        Path wsecondStampPath = Path.of("C:\\Users\\m.denisov\\stamps\\2.txt");

        Path wfirst = Path.of("C:\\Users\\m.denisov\\Documents\\Оборудование");
        Path wsecond = Path.of("D:\\test");
        Path wtestFile = Path.of("C:\\Users\\m.denisov\\Documents\\временные\\Без имени 1.odt");
        Path wthird = Path.of("F:\\1EN-TraceWay\\2.9. Департамент качества\\Проекты");

        Path mfirst = Path.of("/Users/westtochka/Documents/panopticonTest/first");
        Path msecond = Path.of("/Users/westtochka/Documents/panopticonTest/second");


        Researcher researcher = new Researcher();
        Copier copier = new Copier();
        Differences differences = researcher.compare(mfirst, msecond);
        copier.swapAll(differences);
    }

}
