package com.group.original.panopticon.file.system;

import com.group.original.panopticon.exception.ExceptionHandler;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class Differences {
    private DirectoryStamp firstDir;
    private DirectoryStamp secondDir;

    private Set<Path> onlyInFirst = new HashSet<>();
    private Set<Path> onlyInSecond = new HashSet<>();
    private Set<Path> common = new HashSet<>();
    private Set<Path> modifiedFilesForSize = new HashSet<>();
    private Set<Path> modifiedFilesForTime = new HashSet<>();
    private Set<Path> modifiedFilesForMD5 = new HashSet<>();

    public Differences(DirectoryStamp firstDir, DirectoryStamp secondDir) {
        this.firstDir = firstDir;
        this.secondDir = secondDir;
        compare();
    }

    private void compare() {
        comparePaths();
        compareCommonFiles();
    }

    private void comparePaths() {
        Set<Path> firstDirPaths = firstDir.getRelativePaths();
        Set<Path> secondDirPaths = secondDir.getRelativePaths();

        for (Path path : firstDirPaths) {
            if (secondDirPaths.contains(path)) {
                common.add(path);
            } else {
                onlyInFirst.add(path);
            }
        }

        for (Path path : secondDirPaths) {
            if (!common.contains(path)) {
                onlyInSecond.add(path);
            }
        }
    }

    private void compareCommonFiles() {
        try {
            for (Path path : common) {
                if (getSize(firstDir, path) != getSize(secondDir, path)) {
                    modifiedFilesForSize.add(path);
                }

                if (!getLastModifiedTime(firstDir, path).equals(getLastModifiedTime(secondDir, path))) {
                    modifiedFilesForTime.add(path);
                }

                if (!getMD5(firstDir, path).equals(getMD5(secondDir, path))) {
                    modifiedFilesForMD5.add(path);
                }
            }
        } catch (Exception e) {
            ExceptionHandler.outputMessage(e);
        }
    }

    private long getSize(DirectoryStamp directoryStamp, Path relativePath) {
        Path root = directoryStamp.getRoot();
        return directoryStamp.getFileSize(root.resolve(relativePath));
    }

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

    public Set<Path> getOnlyInFirst() {
        return Collections.unmodifiableSet(onlyInFirst);
    }

    public Set<Path> getOnlyInSecond() {
        return Collections.unmodifiableSet(onlyInSecond);
    }

    public Set<Path> getModifiedFilesForSize() {
        return Collections.unmodifiableSet(modifiedFilesForSize);
    }

    public Set<Path> getModifiedFilesForTime() {
        return Collections.unmodifiableSet(modifiedFilesForTime);
    }

    public Set<Path> getModifiedFilesForMD5() {
        return modifiedFilesForMD5;
    }

    public boolean isEqualsInMD5() {
        return isEqualsOnList() && modifiedFilesForMD5.isEmpty();
    }

    public boolean isEqualsInTime() {
        return isEqualsOnList() && modifiedFilesForTime.isEmpty();
    }

    public boolean isEqualsInSize() {
        return isEqualsOnList() && modifiedFilesForSize.isEmpty();
    }

    public boolean isEqualsOnList() {
        return onlyInFirst.isEmpty() && onlyInSecond.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Differences {\r\n")
                .append("First Directory = ")
                .append(firstDir.getRoot())
                .append("\r\n")
                .append("Second Directory = ")
                .append(secondDir.getRoot())
                .append("\r\n");
        System.out.println(builder);
        if (onlyInSecond.size() > 0) {
            builder.append("Files that are not in the first directory, but are present in the second:\r\n");
            for (Path path : onlyInSecond) {
                builder.append("    ")
                        .append(path)
                        .append("\r\n");
            }
        }
        if (onlyInFirst.size() > 0) {
            builder.append("Files that are not in the second directory, but are present in the first:\r\n");
            for (Path path : onlyInFirst) {
                builder.append("    ")
                        .append(path)
                        .append("\r\n");
            }
        }
        if (modifiedFilesForTime.size() > 0) {
            builder.append("Modified files (for time):\r\n");
            for (Path path : modifiedFilesForTime) {
                builder.append("    ")
                        .append(path)
                        .append("\r\n");
            }
        }
        if (modifiedFilesForSize.size() > 0) {
            builder.append("Modified files (for size):\r\n");
            for (Path path : modifiedFilesForSize) {
                builder.append("    ")
                        .append(path)
                        .append("\r\n");
            }
        }
        if (modifiedFilesForMD5.size() > 0) {
            builder.append("Modified files (for md5):\r\n");
            for (Path path : modifiedFilesForSize) {
                builder.append("    ")
                        .append(path)
                        .append("\r\n");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
