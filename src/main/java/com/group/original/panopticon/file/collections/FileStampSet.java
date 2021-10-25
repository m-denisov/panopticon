package com.group.original.panopticon.file.collections;

import com.group.original.panopticon.file.system.FileStamp;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class FileStampSet implements Set<FileStamp> {
    private Map<Path, FileStamp> files;

    public FileStampSet() {
        files = new HashMap<>();
    }

    public FileStampSet(Set<FileStamp> other) {
        this();
        addAll(other);
    }

    public FileStamp get(Path path) {
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
    public Iterator<FileStamp> iterator() {
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
    public boolean add(FileStamp fileStamp) {
        return files.put(fileStamp.getRelativePath(), fileStamp) == null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        if (!(o instanceof FileStamp)) return false;
        FileStamp stamp = (FileStamp) o;
        return files.remove(stamp.getRelativePath(), stamp);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return files.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends FileStamp> c) {
        int startSize = files.size();
        for (FileStamp stamp : c) {
            files.put(stamp.getRelativePath(), stamp);
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
