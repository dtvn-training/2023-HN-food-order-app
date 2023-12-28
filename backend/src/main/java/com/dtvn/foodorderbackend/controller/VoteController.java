package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.model.request.VoteCreateRequest;
import com.dtvn.foodorderbackend.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("vote")
public class VoteController {
    final VoteService voteService;
    final HttpServletResponse response;
    final HttpServletRequest request;

    @PostMapping("/create_vote")
    public ResponseEntity<?> createVote(@RequestBody VoteCreateRequest voteRequest) {
        long userId = Integer.parseInt(String.valueOf(request.getAttribute("user_id")));
        if (voteService.createVote(voteRequest.getRestaurantUrl(), userId, voteRequest.getRestaurantName(), voteRequest.getDescription())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/get_vote_present")
    public ResponseEntity<?> getVotingRestaurant() {
        return ResponseEntity.ok().body(voteService.getVotePresent());
    }

    @PostMapping("/vote_restaurant")
    public ResponseEntity<?> voteRestaurant(@RequestParam("present_vote_id") int presentVoteId) {
        long userId = Integer.parseInt(String.valueOf(request.getAttribute("user_id")));
        if (voteService.insertVoteAction(userId, presentVoteId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Không tìm thấy quán ăn này hoặc bạn đã vote quán này rồi");
    }
}
