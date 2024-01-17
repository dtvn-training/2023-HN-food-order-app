package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.model.dto.request.DishUpdateActive;
import com.dtvn.foodorderbackend.model.dto.response.DishRes;
import com.dtvn.foodorderbackend.service.DishService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dishes")
@CrossOrigin("*")
public class AdminDishController {
    @Autowired
    private DishService dishService;
    @GetMapping
    public ResponseEntity<?> getDishSelected(){
        List<DishRes> response = dishService.getDishes();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActive(
            @PathVariable Long id,
            @RequestBody DishUpdateActive request) {

        request.setId(id);
        if (dishService.updateActive(request)) {
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
