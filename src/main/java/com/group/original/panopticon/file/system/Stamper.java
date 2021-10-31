package com.group.original.panopticon.file.system;

import com.group.original.panopticon.StandardPaths;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Stamper {
    public static final String WIN_OS_NAME_PREFIX = "win";
    public static final String MAC_OS_NAME_PREFIX = "mac";

    public static void writeStamp(DirectoryStamp directoryStamp) {
        if (directoryStamp == null) throw new IllegalArgumentException("stamp is null");

        String stampPath = getStampPath(directoryStamp.getRoot());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(stampPath))) {
            oos.writeObject(directoryStamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DirectoryStamp readStamp(Path directoryPath) {
        String stampPath = getStampPath(directoryPath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(stampPath))) {
            return (DirectoryStamp) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getStampPath(Path directoryPath) {
        String stampName = DigestUtils.md5Hex(directoryPath.toString()) + ".stamp";
        return getStandardStampsPath()
                .resolve(stampName)
                .toString();
    }

    public static Path getStandardStampsPath() {
        if (System.getProperty("os.name").toLowerCase().contains(WIN_OS_NAME_PREFIX)) {
            return StandardPaths.WIN_STAMPS;
        }
        return StandardPaths.MAC_STAMPS;
    }

    public static boolean isStamped(Path directoryPath) {
        String stampPath = getStampPath(directoryPath);
        return Files.exists(Path.of(stampPath));
    }
}
