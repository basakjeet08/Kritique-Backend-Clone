package dev.anirban.kritique.controller;


import dev.anirban.kritique.dto.CustomResponse;
import dev.anirban.kritique.entity.User;
import dev.anirban.kritique.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public CustomResponse<User> createUserHandler(@RequestBody User user) {
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "User Created Successfully",
                userService.createUser(user)
        );
    }

    @GetMapping("/users")
    public CustomResponse<List<User>> findAllUserHandler() {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Users fetched successfully",
                userService.findAllUsers()
        );
    }

    @GetMapping("/users/{id}")
    public CustomResponse<User> findUserByIdHandler(@PathVariable Integer id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "User fetched Successfully",
                userService.findUserById(id)
        );
    }


    @DeleteMapping("/users/{id}")
    public CustomResponse<Void> deleteUserHandler(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "User Deleted Successfully",
                null
        );
    }
}
