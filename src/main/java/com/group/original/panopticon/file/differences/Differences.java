package com.group.original.panopticon.file.differences;

import com.group.original.panopticon.file.ComparisonOrder;
import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.FileStamp;

import java.nio.file.Path;
import java.util.Set;

public class Differences {
    private final StampMatcher matcher;
    private final ComparisonOrder comparisonOrder;

    private Differences(StampMatcher matcher, ComparisonOrder comparisonOrder) {
        this.matcher = matcher;
        this.comparisonOrder = comparisonOrder;
    }

    public static Differences of(StampMatcher matcher, ComparisonOrder comparisonOrder) {
        return new Differences(matcher, comparisonOrder);
    }

    public boolean isDirectOrder() {
        return comparisonOrder.isDirect();
    }

    public boolean isEmpty() {
        return matcher.isEqualsInTime();
    }

    public Set<FileStamp> getOnlyInFirst() {
        if (comparisonOrder.isDirect()) {
            return matcher.getOnlyInFirst();
        } else {
            return matcher.getOnlyInSecond();
        }
    }

    public Set<FileStamp> getOnlyInSecond() {
        if (comparisonOrder.isDirect()) {
            return matcher.getOnlyInSecond();
        } else {
            return matcher.getOnlyInFirst();
        }
    }

    public Set<FileStamp> getModifiedLateInFirst() {
        if (comparisonOrder.isDirect()) {
            return matcher.getModifiedLaterInFirst();
        } else {
            return matcher.getModifiedLaterInSecond();
        }
    }

    public Set<FileStamp> getModifiedLateInSecond() {
        if (comparisonOrder.isDirect()) {
            return matcher.getModifiedLaterInSecond();
        } else {
            return matcher.getModifiedLaterInFirst();
        }
    }

    public Set<Path> getCommonFilesRelativePaths() {
        return matcher.getCommon();
    }

    public Path getFirstDirPath() {
        if (comparisonOrder.isDirect()) {
            return matcher.getFirstDirPath();
        }
        return matcher.getSecondDirPath();
    }

    public Path getSecondDirPath() {
        if (comparisonOrder.isDirect()) {
            return matcher.getSecondDirPath();
        }
        return matcher.getFirstDirPath();
    }


}
