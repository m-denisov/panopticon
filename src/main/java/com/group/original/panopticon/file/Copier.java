package com.group.original.panopticon.file;

import com.group.original.panopticon.StandardPaths;
import com.group.original.panopticon.file.differences.Differences;
import com.group.original.panopticon.file.system.FileStamp;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;

public class Copier {
    public static final String TEMP_FILE_PREFIX = "temp-";

    public static void transferAll(Differences differences, TransferOrder transferOrder) {
        transferNewFiles(differences, transferOrder);
        transferChangedFiles(differences, transferOrder);
    }

    public static void transferNewFiles(Differences differences, TransferOrder transferOrder) {
        boolean isDirect = transferOrder.isDirect();
        Path fromRoot = isDirect ? differences.getFirstDirPath() : differences.getSecondDirPath();
        Path toRoot = isDirect ? differences.getSecondDirPath() : differences.getFirstDirPath();

        Set<FileStamp> onlyInFrom = isDirect ? differences.getOnlyInFirst() : differences.getOnlyInSecond();

        for (FileStamp fileStamp : onlyInFrom) {
            Path fromPath = fromRoot.resolve(fileStamp.getRelativePath());
            Path toPath = toRoot.resolve(fileStamp.getRelativePath());
            String fileName = fileStamp.getRelativePath().getFileName().toString();
            String tempFileSuffix = fileName.concat("-").concat(fileStamp.getFormattedLastModifiedTime());

            try {
                Path temp = Files.createTempFile(StandardPaths.getTempsPath(), TEMP_FILE_PREFIX, tempFileSuffix);
                Files.copy(toPath, temp);
                Files.copy(fromPath, toPath);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void transferChangedFiles(Differences differences, TransferOrder transferOrder) {

    }

    public static void transfer(Path path, TransferOrder transferOrder) {

    }

    public static void openCopy(Path fullPath) {
        String extension;
        String fileName = fullPath.getFileName().toString();
        System.out.println(fileName);
        if (fileName.contains(".")) {
            String[] nameParts = fileName.split("[.]");
            System.out.println(Arrays.toString(nameParts));
            extension = "." + nameParts[nameParts.length-1];
        } else extension = "";

        try {
            Path temp = Files.createTempFile( "temp-", "-copy" + extension);
            Files.deleteIfExists(temp);
            Files.copy(fullPath, temp);
            System.out.println(temp);
            Desktop.getDesktop().open(temp.toFile());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
