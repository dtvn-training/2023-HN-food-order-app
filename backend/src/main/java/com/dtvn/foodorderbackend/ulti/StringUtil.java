package com.dtvn.foodorderbackend.ulti;

import java.util.Random;

public class StringUtil {
    public static final String CHARSET_USERNAME = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    public static final String CHARSET_FULL_NAME = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM ";
    public static final String CHARSET_PASSWORD = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM .,@";
    public static final String number = "0123456789";

    public static String randomOTP() {
        Random r = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(number.charAt(r.nextInt(number.length())));
        }
        return builder.toString();
    }
}
