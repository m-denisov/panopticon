package com.group.original.panopticon.file.system;

import com.group.original.panopticon.exception.ExceptionHandler;
import com.group.original.panopticon.file.attrs.Size;
import com.group.original.panopticon.file.attrs.Time;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FileStampImpl implements Serializable, FileStamp {
    private static final long serialVersionUID = 1;

    private transient DateTimeFormatter formatter;

    private String path;
    private String md5;
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
    private LocalDateTime lastModifiedTime;
    private long size;

    public FileStampImpl(Path path, String md5, BasicFileAttributes basicFileAttributes) {
        if (path == null || basicFileAttributes == null || md5 == null || md5.isBlank()) {
            ExceptionHandler.throwException("null file or attributes");
        }
        this.path = path.toString();
        this.md5 = md5;
        creationTime = toLocalDateTime(basicFileAttributes.creationTime());
        lastAccessTime = toLocalDateTime(basicFileAttributes.lastAccessTime());
        lastModifiedTime = toLocalDateTime(basicFileAttributes.lastModifiedTime());
        size = basicFileAttributes.size();
    }

    public FileStampImpl(Path path, BasicFileAttributes basicFileAttributes) {
        if (path == null || basicFileAttributes == null) {
            ExceptionHandler.throwException("null file or attributes");
        }
        this.path = path.toString();
        this.md5 = "";
        creationTime = toLocalDateTime(basicFileAttributes.creationTime());
        lastAccessTime = toLocalDateTime(basicFileAttributes.lastAccessTime());
        lastModifiedTime = toLocalDateTime(basicFileAttributes.lastModifiedTime());
        size = basicFileAttributes.size();
    }

    public FileStampImpl(String path, String md5, LocalDateTime creationTime, LocalDateTime lastAccessTime, LocalDateTime lastModifiedTime, long size) {
        this.path = path;
        this.md5 = md5;
        this.creationTime = creationTime;
        this.lastAccessTime = lastAccessTime;
        this.lastModifiedTime = lastModifiedTime;
        this.size = size;
    }

    public FileStampImpl(String path, LocalDateTime creationTime, LocalDateTime lastAccessTime, LocalDateTime lastModifiedTime, long size) {
        this.path = path;
        this.md5 = "";
        this.creationTime = creationTime;
        this.lastAccessTime = lastAccessTime;
        this.lastModifiedTime = lastModifiedTime;
        this.size = size;
    }

    private LocalDateTime toLocalDateTime(FileTime fileTime) {
        return Time.ofInstant(fileTime.toInstant());
    }

    @Override
    public Path getPath() {
        return Path.of(path);
    }

    @Override
    public String getMD5() {
        return md5;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    @Override
    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    @Override
    public String getFormattedCreationTime() {
        return Time.formattedDateTime(creationTime);
    }

    @Override
    public String getFormattedLastAccessTime() {
        return Time.formattedDateTime(lastAccessTime);
    }

    @Override
    public String getFormattedLastModifiedTime() {
        return Time.formattedDateTime(lastModifiedTime);
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public String getFormattedSize() {
        return Size.getFormattedSize(size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileStampImpl that = (FileStampImpl) o;
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
