package com.group.original.panopticon.investigator;

import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.DirectoryStamp;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DirectoryInvestigator {
    private static final Map<Path, DirectoryStamp> localStamps = new HashMap<>();
    private static final Map<Path, DirectoryStamp> netStamps = new HashMap<>();
//    private static final long LIFE_TIME = 30; //seconds
    private boolean isDeepAnalysis;
    private StampMatcher matcher;

    public DirectoryInvestigator(AnalysisType analysisType) {
        isDeepAnalysis = analysisType.isBooleanValue();
    }

    public boolean isDiffer(Path localPath, Path netPath) {



        return false;
    }

    public void printDifferences(Path local, Path net) {

    }

    public void printIdentical(Path local, Path net) {

    }

    public boolean isChanged(Path path) {
        return false;
    }

    public void printChanges(Path path) {

    }

    public void printUnchanged(Path path) {

    }

    public void makeStamp(Path path) {

    }

    public boolean isSaved(Path path) {
        return false;
    }

    public boolean isStamped(Path path) {
        return false;
    }

    public boolean isDeepAnalysis() {
        return isDeepAnalysis;
    }

}
