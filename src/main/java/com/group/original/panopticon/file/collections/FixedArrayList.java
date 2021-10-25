package com.group.original.panopticon.file.collections;

import java.util.*;

public class FixedArrayList<E> extends ArrayList<E> {
    private static final int CAPACITY = 10;

    public FixedArrayList() {
        super();
    }

    public FixedArrayList(Collection<E> other) {
        if (other.size() > CAPACITY) {
            throw new RuntimeException();
        }
        for (E e : other) {
            super.add(e);
        }
    }

    @Override
    public boolean add(E e) {
        if (size() >= CAPACITY) {
            remove(0);
        }
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() > CAPACITY) throw new RuntimeException();
        if (c.size() + this.size() > CAPACITY) {
            retainAll(c);
        }
        return super.addAll(c);
    }

}
