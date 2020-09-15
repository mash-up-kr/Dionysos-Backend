package com.dionysos.api.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtil {

    public static final int standardHour = 5;
    public static final int standardMinute = 0;

    public static LocalDateTime getStandardTime(LocalDateTime dateTime) {

        LocalTime time = LocalTime.of(standardHour, standardMinute);

        if (dateTime.getHour() < standardHour) {
            dateTime = dateTime.minusDays(1);
            LocalDate date = dateTime.toLocalDate();

            return LocalDateTime.of(date, time);
        } else {
            LocalDate date = dateTime.toLocalDate();

            return LocalDateTime.of(date, time);
        }
    }
}
