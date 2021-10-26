package com.group.original.panopticon.investigator;

import java.nio.file.Path;

public interface Investigator {
    boolean isDiffer(Path local, Path net);
    void printDifferences(Path local, Path net);
    void printIdentical(Path local, Path net);

    boolean isChanged(Path path);
    void printChanges(Path path);
    void printUnchanged(Path path);
    void makeStamp(Path path);
    boolean isStamped(Path path);

    boolean isDeepAnalysis();
}
