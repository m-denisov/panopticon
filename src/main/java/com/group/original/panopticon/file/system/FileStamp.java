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

public class FileStamp implements Serializable {
    private static final long serialVersionUID = 1;

    private transient DateTimeFormatter formatter;

    private String relativePath;
    private String md5;
    private LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;
    private LocalDateTime lastModifiedTime;
    private long size;

    public FileStamp(Path relativePath, String md5, BasicFileAttributes basicFileAttributes) {
        if (relativePath == null || basicFileAttributes == null || md5 == null || md5.isBlank()) {
            ExceptionHandler.throwException("null file or attributes");
        }
        this.relativePath = relativePath.toString();
        this.md5 = md5;
        creationTime = toLocalDateTime(basicFileAttributes.creationTime());
        lastAccessTime = toLocalDateTime(basicFileAttributes.lastAccessTime());
        lastModifiedTime = toLocalDateTime(basicFileAttributes.lastModifiedTime());
        size = basicFileAttributes.size();
    }

    public FileStamp(Path relativePath, BasicFileAttributes basicFileAttributes) {
        if (relativePath == null || basicFileAttributes == null) {
            ExceptionHandler.throwException("null file or attributes");
        }
        this.relativePath = relativePath.toString();
        this.md5 = "";
        creationTime = toLocalDateTime(basicFileAttributes.creationTime());
        lastAccessTime = toLocalDateTime(basicFileAttributes.lastAccessTime());
        lastModifiedTime = toLocalDateTime(basicFileAttributes.lastModifiedTime());
        size = basicFileAttributes.size();
    }

    public FileStamp(String relativePath, String md5, LocalDateTime creationTime, LocalDateTime lastAccessTime, LocalDateTime lastModifiedTime, long size) {
        this.relativePath = relativePath;
        this.md5 = md5;
        this.creationTime = creationTime;
        this.lastAccessTime = lastAccessTime;
        this.lastModifiedTime = lastModifiedTime;
        this.size = size;
    }

    public FileStamp(String relativePath, LocalDateTime creationTime, LocalDateTime lastAccessTime, LocalDateTime lastModifiedTime, long size) {
        this.relativePath = relativePath;
        this.md5 = "";
        this.creationTime = creationTime;
        this.lastAccessTime = lastAccessTime;
        this.lastModifiedTime = lastModifiedTime;
        this.size = size;
    }

    private LocalDateTime toLocalDateTime(FileTime fileTime) {
        return Time.ofInstant(fileTime.toInstant());
    }

    public Path getRelativePath() {
        return Path.of(relativePath);
    }

    public String getMD5() {
        return md5;
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
        return Time.formattedDateTime(creationTime);
    }

    public String getFormattedLastAccessTime() {
        return Time.formattedDateTime(lastAccessTime);
    }

    public String getFormattedLastModifiedTime() {
        return Time.formattedDateTime(lastModifiedTime);
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
        return relativePath.equals(that.relativePath);
    }

    public boolean deepEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileStamp that = (FileStamp) o;
        return relativePath.equals(that.relativePath) &&
                size == that.size &&
                lastModifiedTime.equals(that.lastModifiedTime) &&
                md5.equals(that.md5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relativePath);
    }

    @Override
    public String toString() {
        return relativePath;
    }
}
