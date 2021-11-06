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
    private Path sourceRoot;
    private Path targetRoot;
    private Set<FileStamp> fileStamps;

    public void swapAll(Differences differences) {
        transferNewFilesBothDirections(differences);
        swapModifiedFilesBothDirections(differences);
    }

    public void transferNewFilesBothDirections(Differences differences) {
       transferNewFilesOneDirection(differences, TransferOrder.DIRECT);
       transferNewFilesOneDirection(differences, TransferOrder.REVERS);
    }

    public void transferNewFilesOneDirection(Differences differences, TransferOrder transferOrder) {
        fillPathFields(differences, transferOrder);
        Set<FileStamp> onlyInOne = transferOrder.isDirect() ?
                differences.getOnlyInFirst() :
                differences.getOnlyInSecond();
        transferSet(onlyInOne);
    }

    private void fillPathFields(Differences differences, TransferOrder transferOrder) {
        boolean isDirect = transferOrder.isDirect();
        sourceRoot = isDirect ? differences.getFirstDirPath() : differences.getSecondDirPath();
        targetRoot = isDirect ? differences.getSecondDirPath() : differences.getFirstDirPath();
    }

    private void transferSet(Set<FileStamp> fileStamps) {
        for (FileStamp fileStamp : fileStamps) {
            transferFile(fileStamp);
        }
    }

    private void transferFile(FileStamp fileStamp) {
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

    public void swapModifiedFilesBothDirections(Differences differences) {
        swapModifiedFilesOneDirection(differences, TransferOrder.DIRECT);
        swapModifiedFilesOneDirection(differences, TransferOrder.REVERS);
    }

    public void swapModifiedFilesOneDirection(Differences differences, TransferOrder transferOrder) {
        fillPathFields(differences, transferOrder);

        Set<FileStamp> modifiedLateInOne = transferOrder == TransferOrder.DIRECT ?
                differences.getModifiedLateInFirst() :
                differences.getModifiedLateInSecond();
        transferSet(modifiedLateInOne);
    }

//    public void transferFile(Differences differences, Path relativePath, TransferOrder transferOrder) {
//       fillPathFields(differences, transferOrder);
//       Path sourcePath = sourceRoot.resolve(relativePath);
//       Path targetPath = targetRoot.resolve(relativePath);
//    }

    public void openCopy(Path fullPath) {
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
