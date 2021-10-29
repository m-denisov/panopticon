package com.group.original.panopticon;

import com.group.original.panopticon.exception.ExceptionHandler;
import com.group.original.panopticon.investigator.AnalysisType;
import com.group.original.panopticon.investigator.Investigator;
import com.group.original.panopticon.output.manager.ConsoleOutputManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class App {

	public static void main( String[] args ) {
        Path wsecondStampPath = Path.of("C:\\Users\\m.denisov\\stamps\\2.txt");

        Path wfirst = Path.of("C:\\Users\\m.denisov\\Documents\\Оборудование");
        Path wsecond = Path.of("D:\\test");
        Path wtestFile = Path.of("C:\\Users\\m.denisov\\Documents\\временные\\Без имени 1.odt");
        Path wthird = Path.of("F:\\1EN-TraceWay\\2.9. Департамент качества\\Проекты");

        Path mfirst = Path.of("/Users/westtochka/Documents/panopticonTest/first");
        Path msecond = Path.of("/Users/westtochka/Documents/panopticonTest/second");

//        DirectoryStamp firstStamp = DirectoryStamp.stampOf(first);
//        DirectoryStamp secondStamp = DirectoryStamp.stampOf(wsecond);
//        Differences differences = new Differences(firstStamp, secondStamp);
//        System.out.println(differences);

//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(wsecondStampPath.toString()))) {
//            oos.writeObject(secondStamp);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }

//        DirectoryStamp mfirstStamp = DirectoryStamp.stampOf(mfirst);
//        System.out.println("__________");
//        Stamper.writeStamp(mfirstStamp);
//        System.out.println("__________");
//        DirectoryStamp read = Stamper.readStamp(mfirst);
//        System.out.println(read);

//        Desktop desktop = Desktop.getDesktop();
//        File file = new File("C:\\Users\\m.denisov\\Documents\\Проекты\\Энгельс\\Энгельс Паспорта\\в новой рамке\\И7.159.00.000ПС (21.051, 000350) перемотчик.pdf");
//        try {
//            desktop.open(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        System.out.println(System.getProperty("os.name"));
    }

    //    public void printDifferences(Path local, Path net) {
//
//    }
//
//    public void printIdentical(Path local, Path net) {
//
//    }
//    public void printChanges(Path path) {
//
//    }
//
//    public void printUnchanged(Path path) {
//
//    }

    private static void initServices() {
        ExceptionHandler.registerOutputManager(new ConsoleOutputManager());
    }
}
