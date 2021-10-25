package com.group.original.panopticon.investigator;

import com.group.original.panopticon.file.matcher.StampMatcher;
import com.group.original.panopticon.file.system.DirectoryStamp;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DirectoryInvestigator implements Investigator {

    private static final long LIFE_TIME = 30; //seconds
    private static final Map<Path, LocalDateTime> localStamps = new HashMap<>();
    private static final Map<Path, LocalDateTime> netStamps = new HashMap<>();
    private DirectoryStamp local;
    private DirectoryStamp net;
    private StampMatcher matcher;

    @Override
    public boolean isDiffer(Path localPath, Path netPath) {
        if (local == null) {
            local = DirectoryStamp.stampOf(localPath);
            localStamps.put(local.getRoot(), LocalDateTime.now());
        }
        if (net == null) {
            net = DirectoryStamp.stampOf(netPath);
            netStamps.put(net.getRoot(), LocalDateTime.now());
        }
        LocalDateTime localTime = localStamps.get(localPath);
        LocalDateTime netTime = netStamps.get(netPath);
        if (localTime != null && LocalDateTime.now().minusSeconds(LIFE_TIME).isBefore(localTime)) {
            // TODO: 25.10.2021
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

}
