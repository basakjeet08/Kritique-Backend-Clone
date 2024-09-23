package dev.anirban.kritique.repository;

import dev.anirban.kritique.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
