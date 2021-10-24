package com.group.original.panopticon.file.system;

import java.nio.file.Path;
import java.time.LocalDateTime;

public interface FileStamp {
    Path getPath();

    String getMD5();

    LocalDateTime getCreationTime();

    LocalDateTime getLastAccessTime();

    LocalDateTime getLastModifiedTime();

    String getFormattedCreationTime();

    String getFormattedLastAccessTime();

    String getFormattedLastModifiedTime();

    long getSize();

    String getFormattedSize();
}
