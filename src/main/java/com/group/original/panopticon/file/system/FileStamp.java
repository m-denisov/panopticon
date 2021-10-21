package com.group.original.panopticon.file.system;

import com.group.original.panopticon.exception.ExceptionHandler;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FileStamp implements Serializable {
    private static final long serialVersionUID = 1;
    private static final String ZONE_ID = "Europe/Moscow";
    private static final String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:dd";

    private transient DateTimeFormatter formatter;

    private String path;
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
    private LocalDateTime lastModifiedTime;
    private long size;

    public FileStamp(Path path, BasicFileAttributes basicFileAttributes) {
        if (path == null || basicFileAttributes == null) {
            ExceptionHandler.throwException("null file or attributes");
        }
        this.path = path.toString();
        creationTime = toLocalDateTime(basicFileAttributes.creationTime());
        lastAccessTime = toLocalDateTime(basicFileAttributes.lastAccessTime());
        lastModifiedTime = toLocalDateTime(basicFileAttributes.lastModifiedTime());
        size = basicFileAttributes.size();
        formatter = DateTimeFormatter.ofPattern(FORMAT_PATTERN);
    }

    public FileStamp(String path, LocalDateTime creationTime, LocalDateTime lastAccessTime, LocalDateTime lastModifiedTime, long size) {
        this.path = path;
        this.creationTime = creationTime;
        this.lastAccessTime = lastAccessTime;
        this.lastModifiedTime = lastModifiedTime;
        this.size = size;
        formatter = DateTimeFormatter.ofPattern(FORMAT_PATTERN);
    }

    private LocalDateTime toLocalDateTime(FileTime fileTime) {
        return LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.of(ZONE_ID));
    }

    public Path getPath() {
        return Path.of(path);
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getFormattedCreationTime() {
        return creationTime.format(formatter);
    }

    public String getFormattedLastAccessTime() {
        return lastAccessTime.format(formatter);
    }

    public String getFormattedLastModifiedTime() {
        return lastModifiedTime.format(formatter);
    }

    public long getSize() {
        return size;
    }

    public String getFormattedSize() {
        return Size.getFormattedSize(size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileStamp that = (FileStamp) o;
        return size == that.size
                && path.equals(that.path)
                && creationTime.equals(that.creationTime)
                && lastModifiedTime.equals(that.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, creationTime, lastModifiedTime, size);
    }

    @Override
    public String toString() {
        return path;
    }
}
