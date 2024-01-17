package com.dtvn.foodorderbackend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishUpdateActive {
    private Long id;
    private Boolean active;
    private String info;
}
