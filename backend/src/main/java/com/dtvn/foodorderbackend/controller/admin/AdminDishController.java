package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.createError;
import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.success;

@RestController
@RequestMapping("/admin/dishes")
@RequiredArgsConstructor
@CrossOrigin
public class AdminDishController {
    final DishService dishService;

    @PostMapping("/approve")
    public ResponseEntity<?> approveDish(@RequestParam("id") long dishId) {
        if (dishService.approveDish(dishId)) {
            return success();
        }
        return createError(HttpStatus.NOT_ACCEPTABLE, "Không tồn tại mon ăn hoặc đã được thêm rồi");
    }

    @PostMapping("disapprove")
    public ResponseEntity<?> disApproveDish(@RequestParam("id") long dishId) {
        if (dishService.disApproveDish(dishId)) {
            return success();
        }
        return createError(HttpStatus.NOT_ACCEPTABLE, "Không tồn tại mon ăn hoặc đã được xóa rồi");
    }
}
