package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.service.BillService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
public class BillController {
    final HttpServletRequest request;
    final BillService billService;
    @GetMapping("/get-bills")
    public ResponseEntity<?> getMyBill() {
        // TODO : add filter if needed
        long userId = Integer.parseInt(String.valueOf(request.getAttribute("user_id")));
        return ResponseEntity.ok().body(billService.getBill(userId));
    }
}
