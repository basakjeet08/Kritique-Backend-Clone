package dev.anirban.kritique.repository;

import dev.anirban.kritique.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, String> {

    Page<Review> findByCreatedBy_Uid(String userId, Pageable page);

    Page<Review> findByCreatedFor_Id(String facultyId, Pageable pageable);
}