package com.clinic.appointmentservice.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parse(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public static String format(LocalDate date) {
        return date.format(FORMATTER);
    }
}
