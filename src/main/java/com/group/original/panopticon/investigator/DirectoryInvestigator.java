package com.group.original.panopticon.investigator;

import com.group.original.panopticon.file.system.DirectoryStamp;

import java.nio.file.Path;

public class DirectoryInvestigator implements Investigator {



    @Override
    public boolean isDiffer(Path local, Path net) {
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

    private class CompDirs {
        private DirectoryStamp local;
        private DirectoryStamp net;
        private boolean isSingle = false;

        private CompDirs(DirectoryStamp net) {
            this.net = net;

            isSingle = true;
        }

        private CompDirs(DirectoryStamp local, DirectoryStamp net) {
            this.local = local;
            this.net = net;
        }

        private boolean isSingle() {
            return isSingle;
        }
    }
}
