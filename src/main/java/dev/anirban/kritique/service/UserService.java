package dev.anirban.kritique.service;


import dev.anirban.kritique.entity.User;
import dev.anirban.kritique.exception.UserNotFound;
import dev.anirban.kritique.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(Integer id) {
        return userRepo
                .findById(id)
                .orElseThrow(() -> new UserNotFound(id));
    }

    public void deleteUser(Integer id) {
        if (!userRepo.existsById(id))
            throw new UserNotFound(id);

        userRepo.deleteById(id);
    }
}
