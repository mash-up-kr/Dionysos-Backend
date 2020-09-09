package com.dionysos.api.common.util;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class DateUtil {

    @Value("${spring.util.date.stand_hour}")
    public static final int stndHr = 6;

    public static LocalDateTime getStandardTime(LocalDateTime dateTime) {

        LocalTime time = LocalTime.of(6, 0);

        if (dateTime.getHour() < stndHr) {
            dateTime = dateTime.minusDays(1);
            LocalDate date = dateTime.toLocalDate();

            return LocalDateTime.of(date, time);
        } else {
            LocalDate date = dateTime.toLocalDate();

            return LocalDateTime.of(date, time);
        }
    }
}
