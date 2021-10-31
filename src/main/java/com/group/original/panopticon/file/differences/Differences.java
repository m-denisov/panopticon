package com.group.original.panopticon.file.differences;

import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.FileStamp;

import java.nio.file.Path;
import java.util.Set;

public class Differences {
    private final StampMatcher matcher;
    private final boolean isDirectOrder;

    private Differences(StampMatcher matcher, ComparisonOrder comparisonOrder) {
        this.matcher = matcher;
        this.isDirectOrder = comparisonOrder.isDirect();
    }

    public static Differences of(StampMatcher matcher, ComparisonOrder comparisonOrder) {
        return new Differences(matcher, comparisonOrder);
    }

    public boolean isDirectOrder() {
        return isDirectOrder;
    }

    public boolean isEmpty() {
        return matcher.isEqualsInTime();
    }

    public Set<FileStamp> getOnlyInFirst() {
        if (isDirectOrder) {
            return matcher.getOnlyInFirst();
        } else {
            return matcher.getOnlyInSecond();
        }
    }

    public Set<FileStamp> getOnlyInSecond() {
        if (isDirectOrder) {
            return matcher.getOnlyInSecond();
        } else {
            return matcher.getOnlyInFirst();
        }
    }

    public Set<FileStamp> getModifiedLateInFirst() {
        if (isDirectOrder) {
            return matcher.getModifiedLaterInFirst();
        } else {
            return matcher.getModifiedLaterInSecond();
        }
    }

    public Set<FileStamp> getModifiedLateInSecond() {
        if (isDirectOrder) {
            return matcher.getModifiedLaterInSecond();
        } else {
            return matcher.getModifiedLaterInFirst();
        }
    }

    public Set<Path> getCommonFilesRelativePaths() {
        return matcher.getCommon();
    }

    public Path getFirstDirPath() {
        if (isDirectOrder) {
            return matcher.getFirstDirPath();
        }
        return matcher.getSecondDirPath();
    }

    public Path getSecondDirPath() {
        if (isDirectOrder) {
            return matcher.getSecondDirPath();
        }
        return matcher.getFirstDirPath();
    }


}
