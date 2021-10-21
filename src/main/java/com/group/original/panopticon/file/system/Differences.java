package com.group.original.panopticon.file.system;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Differences {
    private DirectoryStamp firstDir;
    private DirectoryStamp secondDir;

    private Set<Path> notOnFirst = new HashSet<>();
    private Set<Path> notOnSecond = new HashSet<>();
    private Set<Path> common = new HashSet<>();
    private Set<Path> modifiedFilesStrictly = new HashSet<>();
    private Set<Path> modifiedFilesLoosely = new HashSet<>();

    public Differences(DirectoryStamp firstDir, DirectoryStamp secondDir) {
        this.firstDir = firstDir;
        this.secondDir = secondDir;
        compare();
    }

    private void compare() {

    }

    private void comparePaths() {

    }

    public Set<Path> getNotOnFirst() {
        return notOnFirst;
    }

    public Set<Path> getNotOnSecond() {
        return notOnSecond;
    }

    public Set<Path> getModifiedFilesStrictly() {
        return modifiedFilesStrictly;
    }

    public Set<Path> getModifiedFilesLoosely() {
        return modifiedFilesLoosely;
    }

    public boolean isDifferStrictly() {
        return notOnFirst.size() != 0 || notOnSecond.size() != 0 || modifiedFilesStrictly.size() != 0;
    }

    public boolean isDifferLoosely() {
        return notOnFirst.size() != 0 || notOnSecond.size() != 0 || modifiedFilesLoosely.size() != 0;
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

        if (!isDifferStrictly()) {
            builder.append("No changes.\r\n}");
            return builder.toString();
        }
        if (notOnFirst.size() > 0) {
            builder.append("Files that are not in the first directory, but are present in the second:\r\n");
            for (Path path : notOnFirst) {
                builder.append("    ")
                        .append(secondDir.getRoot().relativize(path))
                        .append("\r\n");
            }
        }
        if (notOnSecond.size() > 0) {
            builder.append("Files that are not in the second directory, but are present in the first:\r\n");
            for (Path path : notOnSecond) {
                builder.append("    ")
                        .append(firstDir.getRoot().relativize(path))
                        .append("\r\n");
            }
        }
        if (modifiedFilesLoosely.size() > 0) {
            builder.append("Modified files (loosely):\r\n");
            for (Path path : modifiedFilesLoosely) {
                builder.append("    ")
                        .append(firstDir.getRoot().relativize(path))
                        .append("\r\n");
            }
        }
        if (modifiedFilesStrictly.size() > 0) {
            builder.append("Modified files (strictly):\r\n");
            for (Path path : modifiedFilesStrictly) {
                builder.append("    ")
                        .append(firstDir.getRoot().relativize(path))
                        .append("\r\n");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
