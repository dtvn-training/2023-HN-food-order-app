package com.dtvn.foodorderbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse {
    @SuppressWarnings(value = "unused")
    private static final Logger logger = LoggerFactory.getLogger(BaseResponse.class);

    public static ResponseEntity<SimpleResponse> createError(HttpStatus status, String message) {
        if (message == null || message.isEmpty()) {
            return createError(status);
        }
        return ResponseEntity.status(status).body(create(message));
    }

    public static ResponseEntity<SimpleResponse> createError(HttpStatus status) {
        return ResponseEntity.status(status).body(create("failed"));
    }

    @SuppressWarnings("unused")
    public static ResponseEntity<SimpleResponse> success(String message) {
        if (message == null || message.isEmpty()) {
            return success();
        }
        return ResponseEntity.ok().body(create(message));
    }

    public static ResponseEntity<SimpleResponse> success() {
        return ResponseEntity.ok().body(create("success"));
    }

    static SimpleResponse create(String message){
        return new SimpleResponse(message);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SimpleResponse{
        String message;
    }
}
