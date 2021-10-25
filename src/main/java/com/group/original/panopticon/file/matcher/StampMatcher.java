package com.group.original.panopticon.file.matcher;

import com.group.original.panopticon.file.collections.FileStampSet;
import com.group.original.panopticon.file.system.DirectoryStamp;
import com.group.original.panopticon.file.system.FileStamp;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class StampMatcher {
    private DirectoryStamp firstDir;
    private DirectoryStamp secondDir;

    private FileStampSet onlyInFirst = new FileStampSet();
    private FileStampSet onlyInSecond = new FileStampSet();
    private Set<Path> common = new HashSet<>();
    //    private Set<Path> modifiedFilesForSize = new HashSet<>();
    private FileStampSet modifiedLaterInFirst = new FileStampSet();
    private FileStampSet modifiedLaterInSecond = new FileStampSet();
    private FileStampSet notModifiedInTime = new FileStampSet();
    private FileStampSet modifiedFilesForMD5 = new FileStampSet();

    public StampMatcher(DirectoryStamp firstDir, DirectoryStamp secondDir) {
        this.firstDir = firstDir;
        this.secondDir = secondDir;
        compare();
    }

    private void compare() {
        FileStampSet firstDirFiles = firstDir.getFiles();
        FileStampSet secondDirFiles = secondDir.getFiles();

        comparePaths(firstDirFiles, secondDirFiles);
        compareCommonFiles(firstDirFiles, secondDirFiles);
    }

    private void comparePaths(FileStampSet firstDirFiles, FileStampSet secondDirFiles) {
        for (FileStamp fileStamp : firstDirFiles) {
            if (secondDirFiles.contains(fileStamp)) {
                common.add(fileStamp.getRelativePath());
            } else {
                onlyInFirst.add(fileStamp);
            }
        }

        for (FileStamp fileStamp : secondDirFiles) {
            if (!common.contains(fileStamp.getRelativePath())) {
                onlyInSecond.add(fileStamp);
            }
        }
    }

    private void compareCommonFiles(FileStampSet firstDirFiles, FileStampSet secondDirFiles) {
        compareCommonFilesInTime(firstDirFiles, secondDirFiles);

        if (firstDir.isDeepStump() && secondDir.isDeepStump()) {
            compareCommonFilesInMD5();
        }
    }

    private void compareCommonFilesInTime(FileStampSet firstDirFiles, FileStampSet secondDirFiles) {

        for (FileStamp first : firstDirFiles) {
            if (common.contains(first.getRelativePath())) {
                FileStamp second = secondDirFiles.get(first.getRelativePath());

                if (first.getLastModifiedTime().isAfter(second.getLastModifiedTime())) {
                    modifiedLaterInFirst.add(first);
                } else if (first.getLastModifiedTime().isBefore(second.getLastModifiedTime())) {
                    modifiedLaterInSecond.add(second);
                } else {
                    notModifiedInTime.add(first);
                }
            }
        }
    }

    private void compareCommonFilesInMD5() {
        //TODO
    }

//    private long getSize(DirectoryStamp directoryStamp, Path relativePath) {
//        Path root = directoryStamp.getRoot();
//        return directoryStamp.getFileSize(root.resolve(relativePath));
//    }

    private LocalDateTime getLastModifiedTime(DirectoryStamp directoryStamp, Path relativePath) throws NoSuchElementException {
        Path root = directoryStamp.getRoot();
        return directoryStamp.getFileLastModifiedTime(root.resolve(relativePath));
    }

    private String getMD5(DirectoryStamp directoryStamp, Path relativePath) throws NoSuchElementException {
        Path root = directoryStamp.getRoot();
        return directoryStamp.getMD5(root.resolve(relativePath));
    }

    public Set<Path> getCommon() {
        return common;
    }

    public Set<FileStamp> getOnlyInFirst() {
        return Collections.unmodifiableSet(onlyInFirst);
    }

    public Set<FileStamp> getOnlyInSecond() {
        return Collections.unmodifiableSet(onlyInSecond);
    }

//    public Set<Path> getModifiedFilesForSize() {
//        return Collections.unmodifiableSet(modifiedFilesForSize);
//    }

    public Set<FileStamp> getModifiedLaterInFirst() {
        return Collections.unmodifiableSet(modifiedLaterInFirst);
    }

    public Set<FileStamp> getModifiedLaterInSecond() {
        return Collections.unmodifiableSet(modifiedLaterInSecond);
    }

    public FileStampSet getNotModifiedInTime() {
        return notModifiedInTime;
    }

    public Set<FileStamp> getModifiedFilesForMD5() {
        return modifiedFilesForMD5;
    }

    public boolean isEqualsInMD5() {
        return isEqualsOnList() && modifiedFilesForMD5.isEmpty();
    }

    public boolean isEqualsInTime() {
        return isEqualsOnList() && modifiedLaterInFirst.isEmpty() && modifiedLaterInSecond.isEmpty();
    }

//    public boolean isEqualsInSize() {
//        return isEqualsOnList() && modifiedFilesForSize.isEmpty();
//    }

    public boolean isEqualsOnList() {
        return onlyInFirst.isEmpty() && onlyInSecond.isEmpty();
    }

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Differences {\r\n")
//                .append("First Directory = ")
//                .append(firstDir.getRoot())
//                .append("\r\n")
//                .append("Second Directory = ")
//                .append(secondDir.getRoot())
//                .append("\r\n");
//        System.out.println(builder);
//        if (onlyInSecond.size() > 0) {
//            builder.append("Files that are not in the first directory, but are present in the second:\r\n");
//            for (Path path : onlyInSecond) {
//                builder.append("    ")
//                        .append(path)
//                        .append("\r\n");
//            }
//        }
//        if (onlyInFirst.size() > 0) {
//            builder.append("Files that are not in the second directory, but are present in the first:\r\n");
//            for (Path path : onlyInFirst) {
//                builder.append("    ")
//                        .append(path)
//                        .append("\r\n");
//            }
//        }
//        if (modifiedLaterInFirst.size() > 0) {
//            builder.append("Modified files (for time):\r\n");
//            for (Path path : modifiedLaterInFirst) {
//                builder.append("    ")
//                        .append(path)
//                        .append("\r\n");
//            }
//        }
//        if (modifiedFilesForSize.size() > 0) {
//            builder.append("Modified files (for size):\r\n");
//            for (Path path : modifiedFilesForSize) {
//                builder.append("    ")
//                        .append(path)
//                        .append("\r\n");
//            }
//        }
//        if (modifiedFilesForMD5.size() > 0) {
//            builder.append("Modified files (for md5):\r\n");
//            for (Path path : modifiedFilesForMD5) {
//                builder.append("    ")
//                        .append(path)
//                        .append("\r\n");
//            }
//        }
//        builder.append("}");
//        return builder.toString();
//    }
}