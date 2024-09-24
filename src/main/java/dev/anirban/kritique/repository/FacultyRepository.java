package dev.anirban.kritique.repository;

import dev.anirban.kritique.entity.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacultyRepository extends JpaRepository<Faculty, String> {
    Page<Faculty> findByNameContaining(String name, Pageable pageable);
}
