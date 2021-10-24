package com.group.original.panopticon.file.collections;

import com.group.original.panopticon.file.system.FileStamp;

import java.util.Collection;

public class UnmodifiedFileStampSet extends FileStampSet {
    public UnmodifiedFileStampSet(Collection<FileStamp> files) {
        super();
    }

    @Override
    public boolean add(FileStamp fileStamp) {
        throw new RuntimeException();
    }

    @Override
    public boolean remove(Object o) {
        throw new RuntimeException();
    }

    @Override
    public void clear() {
        throw new RuntimeException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException();
    }

    @Override
    public boolean addAll(Collection<? extends FileStamp> c) {
        throw new RuntimeException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException();
    }
}
