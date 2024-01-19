package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.dto.response.PresentVoteResponse;
import com.dtvn.foodorderbackend.model.entity.PresentVote;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.entity.UserVote;
import com.dtvn.foodorderbackend.repository.PresentVoteRepository;
import com.dtvn.foodorderbackend.repository.RestaurantRepository;
import com.dtvn.foodorderbackend.repository.UserRepository;
import com.dtvn.foodorderbackend.repository.UserVoteRepository;
import com.dtvn.foodorderbackend.ulti.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VoteService {
    final UserVoteRepository userVoteRepository;
    final RestaurantRepository restaurantRepository;
    final PresentVoteRepository presentVoteRepository;
    final UserRepository userRepository;

    public boolean createVote(String restaurantUrl, long userId, String restaurantName, String description) {
        if (presentVoteRepository.existsByRestaurantUrl(restaurantUrl)) {
            return false;
        }
        PresentVote presentVote = PresentVote
                .builder()
                .restaurantName(restaurantName)
                .description(description)
                .restaurantUrl(restaurantUrl)
                .totalVote(0)
                .active(true)
                .build();
        presentVote.update(userId);
        presentVoteRepository.save(presentVote);
        return insertVoteAction(userId, presentVote.getId());
    }

    public boolean insertVoteAction(long userId, long votePresentId) {
        Timestamp now = TimeUtil.getCurrentTimestamp();
        Timestamp startDay = TimeUtil.getTimestampStartOfDay();
        PresentVote presentVote = presentVoteRepository.findByActiveTrueAndId(votePresentId);
        if (presentVote == null) {
            return false;
        }
        if (userVoteRepository.existsByCreatedByIdAndPresentVoteIdAndVoteTimeGreaterThanAndVoteTimeLessThanEqual(userId, votePresentId, startDay, now)) {
            return false;
        }
        acceptVoteAction(userId, votePresentId);
        return true;
    }

    private void acceptVoteAction(long userId, long presentVoteId) {
        PresentVote presentVote = presentVoteRepository.findById(presentVoteId).orElseThrow();
        presentVote.setTotalVote(presentVote.getTotalVote() + 1);

        presentVote.update(userId);
        presentVoteRepository.save(presentVote);

        UserVote userVote = new UserVote();
        userVote.setVoteTime(TimeUtil.getCurrentTimestamp());
        userVote.setPresentVoteId(presentVoteId);

        userVote.update(userId);
        userVoteRepository.save(userVote);
    }

    public List<PresentVoteResponse> getVotePresent() {
        List<PresentVote> presentVotes = presentVoteRepository.findAllByActiveTrue();
        List<PresentVoteResponse> response = new ArrayList<>();

        for (PresentVote item : presentVotes) {
            User user = userRepository.findUserById(item.getCreatedById()).orElse(null);
            if (user == null) return null;
            response.add(PresentVoteResponse.builder()
                    .id(item.getId())
                    .createdName(user.getFullName())
                    .userIdCreated(user.getId())
                    .totalVote(item.getTotalVote())
                    .restaurantName(item.getRestaurantName())
                    .description(item.getDescription())
                    .restaurantUrl(item.getRestaurantUrl())
                    .build());
        }
        return response;
    }

    public List<PresentVote> getPresentVote(long userId) {
        return presentVoteRepository.findAllByCreatedById(userId);
    }

    public boolean deletePresentVote(long presentVoteId) {
        if (presentVoteRepository.findByActiveTrueAndId(presentVoteId) == null) {
            return false;
        }
        presentVoteRepository.setActiveFalse(presentVoteId);
        return true;
    }
}
