package dev.anirban.kritique.repository;

import dev.anirban.kritique.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("Select r from Review r where r.createdBy.id = :userId")
    List<Review> findReviewByUserId(@Param("userId") String userId);

    @Query("Select r from Review r where r.createdFor.id = :facultyId")
    List<Review> findReviewByFacultyId(@Param("facultyId") String facultyId);
}