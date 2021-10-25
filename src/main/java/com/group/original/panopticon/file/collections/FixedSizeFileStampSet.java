package com.group.original.panopticon.file.collections;

import com.group.original.panopticon.file.system.FileStamp;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class FixedSizeFileStampSet extends FileStampSet {
    private static final int CAPACITY = 10;
    private static final List<FileStamp> list = new ArrayList<>();

    public FixedSizeFileStampSet() {
        super();
    }

    public FixedSizeFileStampSet(Collection<FileStamp> other) {
        if (other.size() > CAPACITY) {
            throw new RuntimeException();
        }
        for (FileStamp fileStamp : other) {
            super.add(fileStamp);
            list.add(fileStamp);
        }
    }

    @Override
    public FileStamp get(Path path) {
        return super.get(path);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public Iterator<FileStamp> iterator() {
        return super.iterator();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return super.toArray(a);
    }

    @Override
    public boolean add(FileStamp fileStamp) {
        if (size() >= CAPACITY) {
            removeFirst();
        }
        list.add(fileStamp);
        return super.add(fileStamp);
    }

    @Override
    public boolean remove(Object o) {
        if (super.remove(o)) {
            return list.remove(o);
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends FileStamp> c) {
        if (c.size() > CAPACITY) throw new RuntimeException();
        if (c.size() + this.size() > CAPACITY) {
            retainAll(c);
        }
        list.addAll(c);
        return super.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        super.retainAll(c);
        return list.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        super.removeAll(c);
        return list.removeAll(c);
    }

    @Override
    public void clear() {
        super.clear();
        list.clear();
    }

    private void removeFirst() {
        FileStamp stamp = list.get(0);
        super.remove(stamp);
        list.remove(0);
    }
}
