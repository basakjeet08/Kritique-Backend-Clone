package dev.anirban.kritique.repository;

import dev.anirban.kritique.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
