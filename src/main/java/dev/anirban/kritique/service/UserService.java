package dev.anirban.kritique.service;

import dev.anirban.kritique.dto.user.UserDTO;
import dev.anirban.kritique.entity.User;
import dev.anirban.kritique.exception.UserNotFound;
import dev.anirban.kritique.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public UserDTO createUser(UserDTO user) {
        return userRepo
                .save(user.toUser())
                .toUserDTO();
    }

    public List<UserDTO> findAllUsers(Pageable pageable) {
        return userRepo
                .findAll(pageable)
                .stream().map(User::toUserDTO)
                .toList();
    }

    public UserDTO findUserById(String id) {
        return userRepo
                .findById(id)
                .map(User::toUserDTO)
                .orElseThrow(() -> new UserNotFound(id));
    }

    public void deleteUser(String id) {
        if (!userRepo.existsById(id))
            throw new UserNotFound(id);

        userRepo.deleteById(id);
    }
}
