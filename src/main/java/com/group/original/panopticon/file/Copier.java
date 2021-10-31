package com.group.original.panopticon.file;

import com.group.original.panopticon.file.differences.Differences;
import com.group.original.panopticon.file.differences.ComparisonOrder;
import com.group.original.panopticon.file.system.FileStamp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class Copier {
    public static final String TEMP_FILE_PREFIX = "temp-";

    public static void transferAll(Differences differences, ComparisonOrder comparisonOrder) {
        boolean isDirect = comparisonOrder.isDirect();
        Path fromRoot = isDirect ? differences.getFirstDirPath() : differences.getSecondDirPath();
        Path toRoot = isDirect ? differences.getSecondDirPath() : differences.getFirstDirPath();

        Set<FileStamp> onlyInFrom = isDirect ? differences.getOnlyInFirst() : differences.getOnlyInSecond();

        for (FileStamp stamp : onlyInFrom) {
            Path fromPath = fromRoot.resolve(stamp.getRelativePath());
            String fileName = fromPath.getFileName().toString();
            Path fromDir = fromPath.getParent();
            try {
                Path temp = Files.createTempFile(fromDir, TEMP_FILE_PREFIX, fileName);
                Files.copy(fromPath, temp);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void transfer(Differences differences, Path path, ComparisonOrder comparisonOrder) {

    }

    public static void openCopy(Path fullPath) {

    }
}
