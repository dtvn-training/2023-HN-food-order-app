package com.dtvn.foodorderbackend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishUpdate {
    private Long id;
    private Boolean active;
    private Boolean approved;
}
