package com.group.original.panopticon.investigator;

import java.nio.file.Path;

public interface Investigator {
    boolean isDiffer(Path local, Path net);
    boolean isChanged(Path path);
    boolean isStamped(Path path);
    void makeStamp(Path path);
    
}
