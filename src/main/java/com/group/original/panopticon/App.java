package com.group.original.panopticon;

import com.group.original.panopticon.exception.ExceptionHandler;
import com.group.original.panopticon.file.system.DirectoryStamp;
import com.group.original.panopticon.output.manager.ConsoleOutputManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class App {

	public static void main( String[] args ) {
        //Path path = Path.of("C:\\Users\\m.denisov\\Documents\\Проекты\\станые, взамен утерянных\\Татхимфармпрепараты\\test");

        Path first = Path.of("D:\\test");
        Path second = Path.of("D:\\test\\1");
        Path testFile = Path.of("C:\\Users\\m.denisov\\Documents\\временные\\Без имени 1.odt");
        Path third = Path.of("F:\\1EN-TraceWay\\2.9. Департамент качества\\Проекты");
//        BasicFileAttributes attributes = null;
//        try {
//            attributes = Files.readAttributes(first, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(attributes.size() + " " + attributes.fileKey() + " " + attributes.lastModifiedTime().toInstant());
//        System.out.println(attributes.lastAccessTime());
//        System.out.println(first.relativize(second));
//        initServices();

        DirectoryStamp directoryStamp = new DirectoryStamp(third);
        System.out.println(directoryStamp.getNumberOfFiles());
        for (Path path : directoryStamp.getRelativePaths()) {
            System.out.println(path);
        }

    }

    private static void initServices() {
        ExceptionHandler.registerOutputManager(new ConsoleOutputManager());
    }
}
