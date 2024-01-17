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
@RequiredArgsConstructor
public class AdminDishController {
    @Autowired
    private DishService dishService;
    final HttpServletRequest request;
    final HttpServletResponse response;
    @GetMapping
    public ResponseEntity<?> getDishSelected(){
        List<DishRes> response = dishService.getDishes();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActive(
            @PathVariable Long id,
            @RequestBody DishUpdateActive request){

        request.setId(id);
        if (dishService.updateActive(request)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.ok("Fail");
    }
}
