package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_vote")
public class UserVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "user_id")
    long userId;

    @Column(name = "present_vote_id")
    long presentVoteId;

    @Column(name = "vote_time")
    Timestamp voteTime;

}
