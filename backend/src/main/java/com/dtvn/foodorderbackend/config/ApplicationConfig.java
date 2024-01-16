package com.dtvn.foodorderbackend.config;

public class ApplicationConfig {
    public static final long EXPIRED_REGISTER_OTP = 2 * 60 * 1000;
    public static final long EXPIRED_RESET_PASSWORD_OTP = 5 * 60 * 1000;
    public static final long EXPIRED_JWT_TOKEN = 30L * 24 * 60 * 60 * 1000;
}
