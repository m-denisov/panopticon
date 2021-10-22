package com.group.original.panopticon;

import com.group.original.panopticon.exception.ExceptionHandler;
import com.group.original.panopticon.file.attrs.Size;
import com.group.original.panopticon.file.attrs.Time;
import com.group.original.panopticon.file.system.Differences;
import com.group.original.panopticon.file.system.DirectoryStamp;
import com.group.original.panopticon.output.manager.ConsoleOutputManager;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main( String[] args ) {
        //Path path = Path.of("C:\\Users\\m.denisov\\Documents\\Проекты\\станые, взамен утерянных\\Татхимфармпрепараты\\test");

        Path secondStampPath = Path.of("C:\\Users\\m.denisov\\stamps\\2.txt");

        Path first = Path.of("C:\\Users\\m.denisov\\Documents\\Оборудование");
        Path second = Path.of("D:\\test");
        Path testFile = Path.of("C:\\Users\\m.denisov\\Documents\\временные\\Без имени 1.odt");
        Path third = Path.of("F:\\1EN-TraceWay\\2.9. Департамент качества\\Проекты");

//        DirectoryStamp firstStamp = DirectoryStamp.stampOf(first);
        DirectoryStamp secondStamp = DirectoryStamp.stampOf(second);
//        Differences differences = new Differences(firstStamp, secondStamp);
//        System.out.println(differences);

//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(secondStampPath.toString()))) {
//            oos.writeObject(secondStamp);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }

        DirectoryStamp stamp = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(secondStampPath.toString()))) {
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
