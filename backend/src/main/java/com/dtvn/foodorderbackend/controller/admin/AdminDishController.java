package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.model.dto.request.DishUpdate;
import com.dtvn.foodorderbackend.model.dto.response.DishRes;
import com.dtvn.foodorderbackend.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.createError;
import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.success;

@RestController
@RequestMapping("/admin/dishes")
@CrossOrigin
public class AdminDishController {
    @Autowired
    private DishService dishService;
    @GetMapping("")
    public ResponseEntity<?> getDishSelected(){
        List<DishRes> response = dishService.getDishes();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody DishUpdate request) {

        request.setId(id);
        if (dishService.update(request)) {
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.ok("Fail");
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approveDish(@RequestParam("id") long dishId) {
        if (dishService.approveDish(dishId)) {
            return success();
        }
        return createError(HttpStatus.NOT_ACCEPTABLE, "Không tồn tại mon ăn hoặc đã được thêm rồi");
    }

    @PostMapping("disapprove")
    public ResponseEntity<?> disApproveDish(@RequestParam("id") long dishId){
        if(dishService.disApproveDish(dishId)){
            return success();
        }
        return createError(HttpStatus.NOT_ACCEPTABLE, "Không tồn tại mon ăn hoặc đã được xóa rồi");

    }
}
