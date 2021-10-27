package com.group.original.panopticon.investigator;

import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.DirectoryStamp;

import java.nio.file.Path;

public class DirectoryInvestigator implements Investigator {
    private static final StampSet<DirectoryStamp> localStamps = new StampSet<>();
    private static final StampSet<DirectoryStamp> netStamps = new StampSet<>();
    private static final long LIFE_TIME = 30; //seconds
    private boolean isDeepAnalysis;
    private StampMatcher matcher;

    public DirectoryInvestigator(AnalysisType analysisType) {
        isDeepAnalysis = analysisType.isBooleanValue();
    }

    @Override
    public boolean isDiffer(Path localPath, Path netPath) {



        return false;
    }

    @Override
    public void printDifferences(Path local, Path net) {

    }

    @Override
    public void printIdentical(Path local, Path net) {

    }

    @Override
    public boolean isChanged(Path path) {
        return false;
    }

    @Override
    public void printChanges(Path path) {

    }

    @Override
    public void printUnchanged(Path path) {

    }

    @Override
    public void makeStamp(Path path) {

    }

    @Override
    public boolean isSaved(Path path) {
        return false;
    }

    @Override
    public boolean isStamped(Path path) {
        return false;
    }

    @Override
    public boolean isDeepAnalysis() {
        return isDeepAnalysis;
    }

}
