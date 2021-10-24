package com.group.original.panopticon;

import com.group.original.panopticon.exception.ExceptionHandler;
import com.group.original.panopticon.file.differences.Differences;
import com.group.original.panopticon.file.system.DirectoryStamp;
import com.group.original.panopticon.output.manager.ConsoleOutputManager;

import java.io.*;
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
        DirectoryStamp secondStamp = DirectoryStamp.stampOf(wsecond);
//        Differences differences = new Differences(firstStamp, secondStamp);
//        System.out.println(differences);

//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(wsecondStampPath.toString()))) {
//            oos.writeObject(secondStamp);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }

        DirectoryStamp stamp = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(wsecondStampPath.toString()))) {
            stamp = (DirectoryStamp) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (stamp != null) {
            Differences differences = new Differences(secondStamp, stamp);
            System.out.println(differences);
        }

    }

    private static void initServices() {
        ExceptionHandler.registerOutputManager(new ConsoleOutputManager());
    }
}
