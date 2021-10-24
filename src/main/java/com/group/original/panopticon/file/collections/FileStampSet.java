package com.group.original.panopticon.file.collections;

import com.group.original.panopticon.file.system.FileStamp;

import java.nio.file.Path;
import java.util.HashSet;

public class FileStampSet extends HashSet<FileStamp> {

    public FileStamp get(Path path) {
        return stream()
                .filter(fileStamp -> fileStamp.getRelativePath().equals(path))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public boolean contains(Object o) {
        if (o == null ) return false;
        if (!(o instanceof Path)) return false;
        Path path = (Path) o;
        return stream()
                .anyMatch(fileStamp -> fileStamp.getRelativePath().equals(path));
    }


}
