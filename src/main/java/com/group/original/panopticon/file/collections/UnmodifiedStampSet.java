package com.group.original.panopticon.file.collections;

import com.group.original.panopticon.file.system.FileStamp;
import com.group.original.panopticon.file.system.Stamp;

import java.util.Collection;

public class UnmodifiedStampSet<E extends Stamp> extends StampSet<E> {
    public UnmodifiedStampSet(Collection<E> files) {
        super();
    }

    @Override
    public boolean add(E fileStamp) {
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
    public boolean addAll(Collection<? extends E> c) {
        throw new RuntimeException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException();
    }
}
