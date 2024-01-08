package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.PresentVote;
import com.dtvn.foodorderbackend.model.entity.UserVote;
import com.dtvn.foodorderbackend.repository.PresentVoteRepository;
import com.dtvn.foodorderbackend.repository.RestaurantRepository;
import com.dtvn.foodorderbackend.repository.UserVoteRepository;
import com.dtvn.foodorderbackend.ulti.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VoteService {
    final UserVoteRepository userVoteRepository;
    final RestaurantRepository restaurantRepository;
    final PresentVoteRepository presentVoteRepository;

    public boolean createVote(String restaurantUrl, long userId, String restaurantName, String description) {
        if (presentVoteRepository.existsByRestaurantUrl(restaurantUrl)) {
            return false;
        }
        PresentVote presentVote = PresentVote
                .builder()
                .userIdCreated(userId)
                .restaurantName(restaurantName)
                .description(description)
                .restaurantUrl(restaurantUrl)
                .totalVote(0)
                .active(true)
                .build();
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
        if (userVoteRepository.existsByUserIdAndPresentVoteIdAndVoteTimeGreaterThanAndVoteTimeLessThanEqual(userId, votePresentId, startDay, now)) {
            return false;
        }
        acceptVoteAction(userId, votePresentId);
        return true;
    }

    private void acceptVoteAction(long userId, long presentVoteId) {
        System.out.println("accepted");
        PresentVote presentVote = presentVoteRepository.findById(presentVoteId).orElseThrow();
        presentVote.setTotalVote(presentVote.getTotalVote() + 1);
        presentVoteRepository.save(presentVote);

        UserVote userVote = new UserVote();
        userVote.setVoteTime(TimeUtil.getCurrentTimestamp());
        userVote.setUserId(userId);
        userVote.setPresentVoteId(presentVoteId);

        userVoteRepository.save(userVote);
    }

    public List<PresentVote> getVotePresent() {
        return presentVoteRepository.findAllByActiveTrue();
    }

    public List<PresentVote> getPresentVote(long userId){
        return presentVoteRepository.findByUserIdCreated(userId);
    }
    public boolean deletePresentVote(long presentVoteId){
        if(presentVoteRepository.findByActiveTrueAndId(presentVoteId)!=null){
            return false;
        }
        presentVoteRepository.setActiveFalse(presentVoteId);
        return true;
    }
}
