package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.model.dto.request.AdminOrderRequest;
import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.model.dto.response.ItemOrderDisplayResponse;
import com.dtvn.foodorderbackend.model.entity.ItemOrder;
import com.dtvn.foodorderbackend.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
@CrossOrigin
public class AdminOrderController {
    final OrderService orderService;
    final AdminController adminController;

    @PostMapping("/order")
    public ResponseEntity<?> adminOrder(@RequestBody AdminOrderRequest adminOrderRequest) throws Exception {
        if (null == orderService.adminOrder(adminOrderRequest)) {
            return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Không thể đặt món, hãy kiểm tra lại hoặc liên hệ bộ phận hỗ trọ");
        }
        return BaseResponse.success("Đã đặt món thành công");
    }

    @GetMapping("/get-list-order")
    public ResponseEntity<?> getAllActiveItemOrder() throws Exception {
        List<ItemOrderDisplayResponse> responses = orderService.getAll().stream().map(ItemOrder::toDisplayResponse).toList();
        return ResponseEntity.ok().body(responses);
    }

    @DeleteMapping("/delete-order-item")
    public ResponseEntity<?> deleteOrder(@RequestParam("order_id") long orderId) throws Exception{
        if(orderService.deleteOrderItem(orderId)){
            return BaseResponse.success("Đã xóa thành công order này");
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE,"Không thể xóa, không tồn tại");
    }
}
