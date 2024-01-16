package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.model.dto.request.TransactionRequest;
import com.dtvn.foodorderbackend.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.createError;
import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.success;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    final HttpServletRequest httpServletRequest;
    final TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<?> postTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionService.saveTransaction(transactionRequest);
        return success("Đã gửi yêu cầu nạp tiền, hãy đợi phê duyệt");
    }

    @GetMapping("/get-transaction")
    public ResponseEntity<?> getTransaction() {
        long userId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
        return ResponseEntity.ok().body(transactionService.getTransaction(userId));
    }

    @DeleteMapping("/delete-transaction")
    public ResponseEntity<?> deleteTransaction(@RequestParam("id") long transactionId) {
        if (transactionService.deleteTransaction(transactionId)) {
            return success();
        }
        return createError(HttpStatus.NOT_ACCEPTABLE, "Không thể xóa");
    }

    @GetMapping("/get-transaction-not-approved")
    public ResponseEntity<?> getTransactionNotApproved() {
        long userId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
        return ResponseEntity.ok().body(transactionService.getTransactionNotApproved(userId));
    }

    @GetMapping("/get-transaction-approved")
    public ResponseEntity<?> getTransactionApproved() {
        long userId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
        return ResponseEntity.ok().body(transactionService.getTransactionApproved(userId));
    }
}
