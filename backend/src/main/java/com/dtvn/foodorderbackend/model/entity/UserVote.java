package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_vote")
public class UserVote extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @JoinColumn(name = "present_vote_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    PresentVote presentVote;

    @Column(name = "present_vote_id")
    long presentVoteId;

    @Column(name = "vote_time")
    Timestamp voteTime;

}
