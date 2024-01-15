package com.dtvn.foodorderbackend.model.dto.request;

import lombok.Data;

@Data
public class TransactionRequest {
    long userId;
    int value;
}
