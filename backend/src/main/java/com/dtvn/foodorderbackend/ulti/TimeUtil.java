package com.dtvn.foodorderbackend.ulti;

import java.sql.Timestamp;
import java.time.LocalDate;

public class TimeUtil {
    public static Timestamp getCurrentTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }
    public static Timestamp getTimestampStartOfDay(){
        return Timestamp.valueOf(LocalDate.now().atStartOfDay());
    }

}
