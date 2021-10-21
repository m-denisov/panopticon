package com.group.original.panopticon.file.system;

public class Size {
    private static final String SIZE_FORMAT = "%d %s";
    public static final long GB = 1024l * 1024l * 1024l;
    public static final long MB = 1024l * 1024l;
    public static final long KB = 1024l;

    public static String getFormattedSize(long size) {
        if (size >= GB) {
            return String.format(SIZE_FORMAT, size / GB, "Gb");
        } else if (size >= MB) {
            return String.format(SIZE_FORMAT, size / MB, "Mb");
        } else if (size >= KB) {
            return String.format(SIZE_FORMAT, size / KB, "Kb");
        } else {
            return String.format(SIZE_FORMAT, size, "b");
        }
    }
}
