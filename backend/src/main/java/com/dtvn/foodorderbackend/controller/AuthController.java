package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.mapper.BaseMapper;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.request.UserRegisterRequest;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    final UserService userService;
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest request){
        logger.debug("{}",request);
        if(userService.checkUserExist(request.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username exist");
        }
        logger.debug("{}",request);

        return ResponseEntity.ok().body(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRegisterRequest request){
        // TODO;
        String username = request.getUsername();
        return null;
    }
}
