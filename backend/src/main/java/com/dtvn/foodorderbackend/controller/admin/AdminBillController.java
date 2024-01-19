package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/bill")
@RequiredArgsConstructor
public class AdminBillController {
    final BillService billService;
    @GetMapping("/get-bills")
    public ResponseEntity<?> getBill(@RequestParam(value = "user_id",required = false) Long userId){
        if(userId != null){
            return ResponseEntity.ok().body(billService.getBill(userId));
        }
        return ResponseEntity.ok().body(billService.getBill());
    }
}
