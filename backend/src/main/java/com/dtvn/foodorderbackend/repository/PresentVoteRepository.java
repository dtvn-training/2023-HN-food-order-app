package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.PresentVote;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PresentVoteRepository extends JpaRepository<PresentVote,Long> {
    boolean existsByRestaurantUrl(String restaurantUrl);

    List<PresentVote> findAllByActiveTrue();

    Optional<PresentVote> findByActiveTrueAndId(long id);
}
