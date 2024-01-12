package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.PresentVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PresentVoteRepository extends JpaRepository<PresentVote, Long> {
    boolean existsByRestaurantUrl(String restaurantUrl);

    List<PresentVote> findAllByActiveTrue();

    List<PresentVote> findAllByCreatedById(long userId);

    @Transactional
    @Modifying
    @Query("update PresentVote p set p.active = false where p.id= :presentVoteId")
    void setActiveFalse(long presentVoteId);

    PresentVote findByActiveTrueAndId(long presentVoteId);
}
