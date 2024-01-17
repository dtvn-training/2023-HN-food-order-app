package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.model.dto.response.SimpleRestaurantResponse;
import com.dtvn.foodorderbackend.model.entity.Restaurant;
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
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/restaurant")
@CrossOrigin
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

    @GetMapping("/get-all-restaurant")
    public ResponseEntity<?> getAllRestaurant(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "selected", required = false) Boolean selected) throws Exception {
        List<SimpleRestaurantResponse> restaurants = restaurantService.getAllRestaurantInDatabase(name, selected);
        return ResponseEntity.ok().body(restaurants);
    }

    @GetMapping("/get-by-criteria")
    public ResponseEntity<?> getRestaurantByCriteria(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "selected", required = false) Boolean selected) throws Exception {
        return ResponseEntity.ok().body(restaurantService.getByCategory(name, selected));
    }

    @PostMapping("/add-restaurant-from-vote-to-database")
    public ResponseEntity<?> addRestaurantFromVotePresentToDatabase(@RequestParam("id") long presentVoteId) throws Exception {
        if (restaurantService.addRestaurantFromVotePresentToDatabase(presentVoteId)) {
            return BaseResponse.success("Đã thêm cửa hàng này");
        }
        return BaseResponse.createError(HttpStatus.NOT_FOUND, "Không thể thêm cửa hàng, hãy kiểm tra lại");
    }

    @PostMapping("/add-restaurant-from-database-to-user-list")
    public ResponseEntity<?> addRestaurantFromDatabaseToUserList(@RequestParam("delivery_id") long deliveryId) throws Exception {
        if (restaurantService.addRestaurantFromDatabaseToUserList(deliveryId)) {
            return BaseResponse.success("Đã thêm cửa hàng này vào danh sách đặt món của NV");
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Lỗi, không thể thêm cửa hàng này");
    }

    @DeleteMapping("/remove-restaurant-from-user-list")
    public ResponseEntity<?> removeRestaurantFromDatabaseToUserList(@RequestParam("delivery_id") long deliveryId) throws Exception {
        if (restaurantService.removeRestaurantFromUserList(deliveryId)) {
            return BaseResponse.success("Đã xóa xửa hàng khỏi danh sách đặt món của NV");
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Lỗi, không thể thêm cửa hàng này");
    }

    @DeleteMapping("/remove-restaurant-from-database")
    public ResponseEntity<?> setRestaurantDeleted(@RequestParam("delivery_id") long deliveryId) throws Exception {
        if (restaurantService.setRestaurantDeleted(deliveryId)) {
            return BaseResponse.success("Đã ẩn cửa hàng này, nếu muốn hiển thị lại, hãy gọi API khác");
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Lỗi, không thể ẩn cửa hàng này");
    }

    @PostMapping("/delete-present-vote")
//    @Operation(description = "id: vote present id")
    public ResponseEntity<?> deletePresentVote(@RequestParam("id") long presentVoteId) throws Exception {
        if (voteService.deletePresentVote(presentVoteId)) {
            return BaseResponse.success("Đã xóa cửa hàng này");
        }
        return BaseResponse.createError(HttpStatus.NOT_FOUND, "Không tồn tại cửa hàng, hãy làm mới trang");
    }
    @PostMapping("/fetch-data")
    public ResponseEntity<?> fetchDataByAdmin(@RequestParam("url") @NonNull String url) throws Exception {
        restaurantService.fetchData(url);
        return BaseResponse.createError(HttpStatus.BAD_REQUEST, "Không thể cập nhật món ăn, hãy thử lại");
    }
}
