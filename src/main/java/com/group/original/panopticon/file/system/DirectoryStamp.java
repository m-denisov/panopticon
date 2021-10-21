package com.group.original.panopticon.file.system;

import com.group.original.panopticon.exception.ExceptionHandler;
import com.group.original.panopticon.file.attrs.Size;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DirectoryStamp implements Serializable {
    private static final long serialVersionUID = 2;

    private String root;
    private Set<FileStamp> files;

    public DirectoryStamp(Path root) throws RuntimeException {
        if (root == null) ExceptionHandler.throwException("root can not be null");
        if (Files.notExists(root)) ExceptionHandler.throwException("root not exists");
        this.root = root.toString();
        makeImage();
    }

    public DirectoryStamp(String root) throws RuntimeException {
        if (root == null) ExceptionHandler.throwException("root can not be null");
        if (Files.notExists(Path.of(root))) ExceptionHandler.throwException("root not exists");
        this.root = root;
        makeImage();
    }

    private void makeImage() throws RuntimeException {
        try {
            files = Files.walk(Path.of(root))
                    .filter(Files::isRegularFile)
                    .map(path -> new FileStamp(path, readAttributes(path)))
                    .collect(Collectors.toUnmodifiableSet());
        } catch (IOException e) {
            ExceptionHandler.outputMessage(e);
        }
    }

    private BasicFileAttributes readAttributes(Path path) {
        try {
            return Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public Path getRoot() {
        return Path.of(root);
    }

    public Set<FileStamp> getFiles() {
        return files;
    }

    public String getFormattedSize() {
        return Size.getFormattedSize(getSize());
    }

    public long getFileSize(Path path) throws NoSuchElementException {
        if (getPaths().contains(path)) {
            return getFile(path).getSize();
        }
        throw new NoSuchElementException();
    }

    public LocalDateTime getFileLastModifiedTime(Path path)  throws NoSuchElementException {
        if (getPaths().contains(path)) {
            return getFile(path).getLastModifiedTime();
        }
        throw new NoSuchElementException();
    }

    public BufferedReader getBufferedReader(Path path) throws NoSuchElementException, IOException {
        if (getPaths().contains(path)) {
            return getFile(path).getBufferedReader();
        }
        throw new NoSuchElementException();
    }

    public long getSize() {
        return files.stream()
                .mapToLong(FileStamp::getSize)
                .sum();
    }

    public Set<Path> getPaths() {
        return files.stream()
                .map(FileStamp::getPath)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<Path> getRelativePaths() {
        return files.stream()
                .map(this::relativizePath)
                .collect(Collectors.toUnmodifiableSet());
    }

    public int getNumberOfFiles() {
        return files.size();
    }

    private Path relativizePath(FileStamp fileStamp) {
        return Path.of(root).relativize(fileStamp.getPath());
    }

    private FileStamp getFile(Path path) {
        return files.stream()
                .filter(fileStamp -> fileStamp.getPath().equals(path))
                .findAny()
                .get();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Root: ")
                .append(root)
                .append("\r\nFiles: \r\n");

        for (FileStamp fileStamp : files) {
            builder.append(" Path: ")
                    .append(relativizePath(fileStamp))
                    .append("\r\n")
                    .append("   Last modified time: ")
                    .append(fileStamp.getFormattedLastModifiedTime())
                    .append("\r\n")
                    .append("   Size: ")
                    .append(fileStamp.getFormattedSize())
                    .append("\r\n");
        }

        return "DirectoryStamp { " +
                builder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryStamp that = (DirectoryStamp) o;
        return root.equals(that.root) && files.equals(that.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, files);
    }
}
