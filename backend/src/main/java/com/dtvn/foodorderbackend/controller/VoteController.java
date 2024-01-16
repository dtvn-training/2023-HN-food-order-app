package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.model.dto.request.VoteCreateRequest;
import com.dtvn.foodorderbackend.model.dto.response.PresentVoteResponse;
import com.dtvn.foodorderbackend.model.entity.PresentVote;
import com.dtvn.foodorderbackend.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.createError;
import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.success;

@Controller
@RequiredArgsConstructor
@RequestMapping("vote")
public class VoteController {
    final VoteService voteService;
    final HttpServletResponse response;
    final HttpServletRequest request;

    @PostMapping("/create-vote")
    public ResponseEntity<?> createVote(@RequestBody VoteCreateRequest voteRequest) {
        long userId = Integer.parseInt(String.valueOf(request.getAttribute("user_id")));
        if (!voteService.createVote(voteRequest.getRestaurantUrl(), userId, voteRequest.getRestaurantName(), voteRequest.getDescription())) {
            return createError(HttpStatus.CONFLICT, "Vote đã tồn tại");
        }
        return success();
    }

    @GetMapping("/get-vote-present")
    public ResponseEntity<?> getVotingRestaurant() {
        return ResponseEntity.ok().body(voteService.getVotePresent().stream().map(PresentVoteResponse::new));
    }

    @PostMapping("/vote-restaurant")
    public ResponseEntity<?> voteRestaurant(@RequestParam("present_vote_id") int presentVoteId) {
        long userId = Integer.parseInt(String.valueOf(request.getAttribute("user_id")));
        if (voteService.insertVoteAction(userId, presentVoteId)) {
            return success("Đã vote quán "+ presentVoteId);
        }
        return createError(HttpStatus.NOT_ACCEPTABLE, "Không tìm thấy quán ăn này hoặc bạn đã vote quán này rồi");
    }

    @GetMapping("/get-my-vote")
    public ResponseEntity<?> getMyVote() {
        long userId = Integer.parseInt(String.valueOf(request.getAttribute("user_id")));
        return ResponseEntity.ok().body(voteService.getPresentVote(userId));
    }
}
