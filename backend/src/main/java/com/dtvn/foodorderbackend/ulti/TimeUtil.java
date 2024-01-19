package com.dtvn.foodorderbackend.ulti;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static Timestamp getCurrentTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }
    public static Timestamp getTimestampStartOfDay(){
        return Timestamp.valueOf(LocalDate.now().atStartOfDay());
    }
    public static String formatToString(LocalDateTime dateTime, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

}
