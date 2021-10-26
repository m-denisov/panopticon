package com.group.original.panopticon.investigator;

import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.DirectoryStamp;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DirectoryInvestigator implements Investigator {
    private static final Map<DirectoryStamp, LocalDateTime> localStamps = new HashMap<>();
    private static final Map<DirectoryStamp, LocalDateTime> netStamps = new HashMap<>();
    private static final long LIFE_TIME = 30; //seconds
    private boolean isDeepAnalysis;
    private DirectoryStamp local;
    private DirectoryStamp net;
    private StampMatcher matcher;

    public DirectoryInvestigator(AnalysisType analysisType) {
        isDeepAnalysis = analysisType.isBooleanValue();
    }

    @Override
    public boolean isDiffer(Path localPath, Path netPath) {
        if (local == null) {
            local = DirectoryStamp.stampOf(localPath);
        }
        if (net == null) {
            net = DirectoryStamp.stampOf(netPath);
        }


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
    public boolean isStamped(Path path) {
        return false;
    }

    @Override
    public boolean isDeepAnalysis() {
        return isDeepAnalysis;
    }

}
