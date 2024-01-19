package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.createError;
import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.success;

@RestController
@RequestMapping("/admin/api/v1/transaction")
@RequiredArgsConstructor
public class AdminTransactionController {
    final AdminController adminController;
    final TransactionService transactionService;
    final HttpServletRequest httpServletRequest;

    @PostMapping("/approve")
    public ResponseEntity<?> approveTransaction(@RequestParam("id") long transactionId) {
        if (transactionService.approveTransaction(transactionId)) {
            return success("Thành công");
        }
        return createError(HttpStatus.NOT_ACCEPTABLE, "Không thể nạp tiền, xin hãy xem lại");
    }

    @GetMapping("/get-not-approved-transaction")
    public ResponseEntity<?> getNotApprovedTransaction() {
        return ResponseEntity.ok().body(transactionService.getTransactionNotApproved());
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTransaction() {
        return ResponseEntity.ok().body(transactionService.getAllTransaction());
    }

    @GetMapping("/get-transaction-approved-by-me")
    public ResponseEntity<?> getTransactionApprovedByMe() {
        long adminId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));

        return ResponseEntity.ok().body(transactionService.getTransactionApprovedBy(adminId));
    }
}
