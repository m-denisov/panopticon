package com.group.original.panopticon.file.attrs;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Time {
    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Moscow");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyy-MM-dd");

    public static LocalDateTime ofInstant(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZONE_ID);
    }

    public static String formattedDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    public static String formattedDate(LocalDateTime localDateTime) {
        return localDateTime.format(TIME_FORMATTER);
    }
}
