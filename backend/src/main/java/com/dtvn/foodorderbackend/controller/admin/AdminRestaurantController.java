package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.model.dto.response.SimpleRestaurantResponse;
import com.dtvn.foodorderbackend.service.RestaurantService;
import com.dtvn.foodorderbackend.service.ShopeeFoodService;
import com.dtvn.foodorderbackend.service.UserService;
import com.dtvn.foodorderbackend.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/restaurant")
public class AdminRestaurantController {
    final UserService userService;
    final HttpServletRequest request;
    final HttpServletResponse response;
    final Mapper mapper;
    final ShopeeFoodService shopeeFoodService;
    final RestaurantService restaurantService;
    final AdminController adminController;
    final VoteService voteService;
    @SuppressWarnings("unused")
    final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/get_all_restaurant")
    public ResponseEntity<?> getAllRestaurant() throws Exception {
        adminController.requireAdminRole();
        List<SimpleRestaurantResponse> restaurants = restaurantService.getAllRestaurantInDatabase();
        return ResponseEntity.ok().body(restaurants);
    }

    @PostMapping("/add_restaurant_from_vote_to_database")
//    @Operation(description = "id: vote present id")
    public ResponseEntity<?> addRestaurantFromVotePresentToDatabase(@RequestParam("id") long presentVoteId) throws Exception {
        adminController.requireAdminRole();
        if (restaurantService.addRestaurantFromVotePresentToDatabase(presentVoteId)) {
            return BaseResponse.success("Đã thêm cửa hàng này");
        }
        return BaseResponse.createError(HttpStatus.NOT_FOUND, "Không thể thêm cửa hàng, hãy kiểm tra lại");
    }

    @PostMapping("/add_restaurant_from_database_to_user_list")
    public ResponseEntity<?> addRestaurantFromDatabaseToUserList(@RequestParam("delivery_id") long deliveryId) throws Exception {
        adminController.requireAdminRole();
        if (restaurantService.addRestaurantFromDatabaseToUserList(deliveryId)) {
            return BaseResponse.success("Đã thêm cửa hàng này vào danh sách đặt món của NV");
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Lỗi, không thể thêm cửa hàng này");
    }

    @DeleteMapping("/remove_restaurant_from_user_list")
    public ResponseEntity<?> removeRestaurantFromDatabaseToUserList(@RequestParam("delivery_id") long deliveryId) throws Exception {
        adminController.requireAdminRole();
        if (restaurantService.removeRestaurantFromUserList(deliveryId)) {
            return BaseResponse.success("Đã xóa xửa hàng khỏi danh sách đặt món của NV");
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Lỗi, không thể thêm cửa hàng này");
    }

    @DeleteMapping("/remove_restaurant_from_database")
    public ResponseEntity<?> setRestaurantDeleted(@RequestParam("delivery_id") long deliveryId) throws Exception {
        adminController.requireAdminRole();
        if (restaurantService.setRestaurantDeleted(deliveryId)) {
            return BaseResponse.success("Đã ẩn cửa hàng này, nếu muốn hiển thị lại, hãy gọi API khác");
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Lỗi, không thể ẩn cửa hàng này");
    }

    @PostMapping("/delete_present_vote")
//    @Operation(description = "id: vote present id")
    public ResponseEntity<?> deletePresentVote(@RequestParam("id") long presentVoteId) throws Exception {
        adminController.requireAdminRole();
        if (voteService.deletePresentVote(presentVoteId)) {
            return BaseResponse.success("Đã xóa cửa hàng này");
        }
        return BaseResponse.createError(HttpStatus.NOT_FOUND, "Không tồn tại cửa hàng, hãy làm mới trang");
    }
}
