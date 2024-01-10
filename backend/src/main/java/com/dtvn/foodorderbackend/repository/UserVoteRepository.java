package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface UserVoteRepository extends JpaRepository<UserVote,Long> {
    boolean existsByCreatedByIdAndPresentVoteIdAndVoteTimeGreaterThanAndVoteTimeLessThanEqual(long userId, long presentVoteId, Timestamp beginTime,Timestamp endTime);
}
