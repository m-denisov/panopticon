package com.group.original.panopticon.file.system;

import com.group.original.panopticon.exception.ExceptionHandler;
import com.group.original.panopticon.file.attrs.Size;
import com.group.original.panopticon.file.collections.StampSet;
import com.group.original.panopticon.file.collections.UnmodifiedStampSet;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DirectoryStamp implements Serializable, Stamp {
    private static final long serialVersionUID = 2;
    private final String root;
    private LocalDateTime creationTime;
    private StampSet<FileStamp> files;
    private boolean isDeepStump = false;

    public static DirectoryStamp stampOf(Path root) {
        DirectoryStamp stamp = new DirectoryStamp(root);
        stamp.readDirectory();
        stamp.setCreationTimeNow();
        return stamp;
    }

    public static DirectoryStamp stampOf(String root) {
        DirectoryStamp stamp = new DirectoryStamp(root);
        stamp.readDirectory();
        stamp.setCreationTimeNow();
        return stamp;
    }

    public static DirectoryStamp deepStampOf(Path root) {
        DirectoryStamp stamp = new DirectoryStamp(root);
        stamp.readDirectoryDeeply();
        stamp.setDeepStump(true);
        stamp.setCreationTimeNow();
        return stamp;
    }

    public static DirectoryStamp deepStampOf(String root) {
        DirectoryStamp stamp = new DirectoryStamp(root);
        stamp.readDirectoryDeeply();
        stamp.setDeepStump(true);
        stamp.setCreationTimeNow();
        return stamp;
    }

    private DirectoryStamp(Path root) throws RuntimeException {
        if (root == null) ExceptionHandler.throwException("root can not be null");
        if (Files.notExists(root)) ExceptionHandler.throwException("root not exists");
        this.root = root.toString();
    }

    private DirectoryStamp(String root) throws RuntimeException {
        if (root == null) ExceptionHandler.throwException("root can not be null");
        if (Files.notExists(Path.of(root))) ExceptionHandler.throwException("root not exists");
        this.root = root;
    }

    private void readDirectory() throws RuntimeException {
        try {
            files = Files.walk(Path.of(root))
                    .filter(Files::isRegularFile)
                    .map(path -> new FileStamp(Path.of(root).relativize(path), readAttributes(path)))
                    .peek(System.out::println)
                    .collect(Collectors.toCollection(StampSet::new));
        } catch (IOException e) {
            ExceptionHandler.outputMessage(e);
        }
    }

    private void readDirectoryDeeply() throws RuntimeException {
        try {
            files = Files.walk(Path.of(root))
                    .filter(Files::isRegularFile)
                    .map(path -> new FileStamp(Path.of(root).relativize(path), readMD5(path), readAttributes(path)))
                    .peek(System.out::println)
                    .collect(Collectors.toCollection(StampSet::new));
        } catch (IOException e) {
            ExceptionHandler.outputMessage(e);
        }
    }

    private String readMD5(Path path) {
        String md5 = "";
        try (InputStream inputStream = Files.newInputStream(path)) {
            md5 = DigestUtils.md5Hex(inputStream);
        } catch (IOException ioException) {
            ExceptionHandler.outputMessage(ioException);
        }
        return md5;
    }

    private BasicFileAttributes readAttributes(Path path) throws RuntimeException {
        try {
            return Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public Path getPath() {
        return Path.of(root);
    }

    public String getRootAsMD5() {
        return DigestUtils.md5Hex(root);
    }

    public StampSet<FileStamp> getFiles() {
        return new UnmodifiedStampSet<>(files);
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

    public LocalDateTime getFileLastModifiedTime(Path path) throws NoSuchElementException {
        if (getPaths().contains(path)) {
            return getFile(path).getLastModifiedTime();
        }
        throw new NoSuchElementException();
    }

    public String getMD5(Path path) throws NoSuchElementException {
        if (getPaths().contains(path)) {
            return getFile(path).getMD5();
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
                .map(FileStamp::getPath)
                .collect(Collectors.toUnmodifiableSet());
    }

    public int getNumberOfFiles() {
        return files.size();
    }

    public FileStamp getFile(Path path) {
        if (path == null || Files.notExists(path)) {
            ExceptionHandler.throwException("path is null of not exist");
        }
        return files.stream()
                .filter(fileStamp -> fileStamp.getPath().equals(path))
                .findAny()
                .orElseThrow();
    }

    public boolean isDeepStump() {
        return isDeepStump;
    }

    private void setDeepStump(boolean deepStump) {
        isDeepStump = deepStump;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    private void setCreationTimeNow() {
        this.creationTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Root: ")
                .append(root)
                .append("\r\nFiles: \r\n");

        for (FileStamp fileStamp : files) {
            builder.append(" Path: ")
                    .append(fileStamp)
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
