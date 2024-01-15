package com.dtvn.foodorderbackend.model.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    long id;
    long userId;
    int value;
    boolean approved;
}
