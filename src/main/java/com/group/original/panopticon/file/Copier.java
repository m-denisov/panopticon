package com.group.original.panopticon.file;

import com.group.original.panopticon.file.differences.Differences;
import com.group.original.panopticon.file.system.FileStamp;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;

public class Copier {
    public static void transferAll(Differences differences, TransferOrder transferOrder) {
        transferFilesOnlyInOnePath(differences, transferOrder);
        transferFilesOnlyInOnePath(differences, transferOrder.getOpposite());
        transferChangedFiles(differences, transferOrder);
    }

    public static void transferFilesOnlyInOnePath(Differences differences, TransferOrder transferOrder) {
        boolean isDirect = transferOrder.isDirect();
        Path sourceRoot = isDirect ? differences.getFirstDirPath() : differences.getSecondDirPath();
        Path targetRoot = isDirect ? differences.getSecondDirPath() : differences.getFirstDirPath();

        Set<FileStamp> onlyInSource = isDirect ? differences.getOnlyInFirst() : differences.getOnlyInSecond();

        for (FileStamp fileStamp : onlyInSource) {
            Path sourcePath = sourceRoot.resolve(fileStamp.getRelativePath());
            Path targetPath = targetRoot.resolve(fileStamp.getRelativePath());

            String fileName = fileStamp.getRelativePath().getFileName().toString();
            String oldFileName = fileStamp.getFormattedLastModifiedTime().concat("-").concat(fileName);
            Path oldVersion = Path.of(oldFileName);

            try {
                Files.deleteIfExists(oldVersion);
                Files.copy(targetPath, oldVersion);

                Files.delete(targetPath);
                Files.copy(sourcePath, targetPath);
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
