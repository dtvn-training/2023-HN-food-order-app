package com.dtvn.foodorderbackend.ulti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static boolean isNumber(char x) {
        return '0' <= x && x <= '9';
    }

    public static String getFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine())!=null){
            builder.append(line);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
