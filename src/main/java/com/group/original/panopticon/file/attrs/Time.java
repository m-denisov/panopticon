package com.group.original.panopticon.file.attrs;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Time {
    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Moscow");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd");

    public static LocalDateTime ofInstant(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZONE_ID);
    }

    public static String formattedDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }
}
