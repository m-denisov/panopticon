package com.group.original.panopticon.investigator;

import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.DirectoryStamp;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Investigator {
    private static final Map<Path, DirectoryStamp> localStamps = new HashMap<>();
    private static final Map<Path, DirectoryStamp> netStamps = new HashMap<>();
    private static final long LIFE_TIME = 1;
    private static final ChronoUnit LIFE_TIME_UNIT = ChronoUnit.MINUTES;
//    private boolean isDeepAnalysis;
    private Map<Connection, StampMatcher> matchers;

//    public Investigator(AnalysisType analysisType) {
//        isDeepAnalysis = analysisType.isBooleanValue();
//    }

    public boolean isDiffer(Path localPath, Path netPath) {
        if (isMatched(localPath, netPath)) {

        }


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

//    public boolean isDeepAnalysis() {
//        return isDeepAnalysis;
//    }

    private String makeStringForPaths(Path local, Path net) {
        return local.toString() + net.toString();
    }

    private boolean isMatched(Path local, Path net) {
        return matchers.get(new Connection(local, net)) != null;
    }

    private boolean isFresh(LocalDateTime localDateTime) {
        return LocalDateTime.now().isBefore(localDateTime.plus(LIFE_TIME, LIFE_TIME_UNIT));
    }

    public boolean isStamped(Path path) {
        // TODO: 28.10.2021
        return false;
    }

    private class Connection {
        String first;
        String second;

        public Connection(Path first, Path second) {
            this.first = first.toString();
            this.second = second.toString();
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Connection that = (Connection) o;
            return (first.equals(that.first) && second.equals(that.second))
                    || (first.equals(that.second) && second.equals(that.first));
        }

        @Override
        public int hashCode() {
            return Math.max(Objects.hash(first), Objects.hash(second));
        }
    }
}
