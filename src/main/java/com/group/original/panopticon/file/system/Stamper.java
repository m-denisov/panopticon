package com.group.original.panopticon.file.system;

import com.group.original.panopticon.StandardPaths;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.nio.file.Path;

public class Stamper {
    public static void writeStamp(DirectoryStamp directoryStamp) {
        if (directoryStamp == null) throw new IllegalArgumentException("stamp is null");

        String stampName = directoryStamp.getRootAsMD5() + ".stamp";
        String stampPath = StandardPaths.MAC_STAMPS.resolve(stampName).toString();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(stampPath))) {
            oos.writeObject(directoryStamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DirectoryStamp readStamp(Path directoryPath) {
        String stampName = DigestUtils.md5Hex(directoryPath.toString()) + ".stamp";
        String stampPath = StandardPaths.MAC_STAMPS.resolve(stampName).toString();


        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(stampPath))) {
            return (DirectoryStamp) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
