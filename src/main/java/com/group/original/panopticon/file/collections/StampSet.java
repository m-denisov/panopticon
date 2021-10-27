package com.group.original.panopticon.file.collections;

import com.group.original.panopticon.file.system.Stamp;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class StampSet<E extends Stamp> implements Set<E> {
    protected Map<Path, E> files;

    public StampSet(Collection<E> collection) {
        this();
        addAll(collection);
    }

    public StampSet() {
        files = new HashMap<>();
    }

    public E get(Path path) {
        return files.get(path);
    }

    @Override
    public int size() {
        return files.size();
    }

    @Override
    public boolean isEmpty() {
        return files.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return files.containsValue(o);
    }

    @Override
    public Iterator<E> iterator() {
        return files.values().iterator();
    }

    @Override
    public Object[] toArray() {
        return files.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return files.values().toArray(a);
    }

    @Override
    public boolean add(E stamp) {
        return files.put(stamp.getPath(), stamp) == null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        if (!(o instanceof Stamp)) return false;
        Stamp stamp = (Stamp) o;
        return files.remove(stamp.getPath(), stamp);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return files.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int startSize = files.size();
        for (E stamp : c) {
            files.put(stamp.getPath(), stamp);
        }
        return files.size() > startSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int startSize = files.size();
        files = files.entrySet().stream()
                .filter(entry -> c.contains(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return files.size() < startSize;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int startSize = files.size();
        files = files.entrySet().stream()
                .filter(entry -> !c.contains(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return files.size() < startSize;
    }

    @Override
    public void clear() {
        files.clear();
    }
}
