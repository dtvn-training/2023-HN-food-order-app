package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.service.RestaurantService;
import com.dtvn.foodorderbackend.service.ShopeeFoodService;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")

@RequiredArgsConstructor
public class AdminController {
    final UserService userService;
    final HttpServletRequest request;
    final HttpServletResponse response;
    final Mapper mapper;
    final ShopeeFoodService shopeeFoodService;
    final RestaurantService restaurantService;
    @SuppressWarnings("unused")
    final Logger logger = LoggerFactory.getLogger(AdminController.class);
    static SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority(User.Role.ADMIN.name());



    @PostMapping("/fetch_data")
    public ResponseEntity<?> fetchDataByAdmin(@RequestParam("url") @NonNull String url) throws Exception {
        requireAdminRole();
        if (shopeeFoodService.fetchRestaurantData(url)) {
            return BaseResponse.success();
        }
        return BaseResponse.createError(HttpStatus.BAD_REQUEST, "Không thể cập nhật món ăn, hãy thử lại");
    }

    void requireAdminRole() throws Exception {
        if (userService.loadUserByUsername(String.valueOf(request.getAttribute("email"))).getAuthorities().contains(ADMIN)) {
            return;
        }
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write("Bạn không có quyền làm việc này");
        throw new Exception("User not admin");
    }

}
