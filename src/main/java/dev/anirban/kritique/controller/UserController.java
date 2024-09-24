package dev.anirban.kritique.controller;


import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.common.CustomResponse;
import dev.anirban.kritique.dto.user.UserDTO;
import dev.anirban.kritique.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(UrlConstants.CREATE_USER)
    public CustomResponse<UserDTO> createUserHandler(@RequestBody UserDTO user) {
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "User Created Successfully",
                userService.createUser(user)
        );
    }

    @GetMapping(UrlConstants.FIND_ALL_USERS)
    public CustomResponse<List<UserDTO>> findAllUserHandler() {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Users fetched successfully",
                userService.findAllUsers()
        );
    }

    @GetMapping(UrlConstants.FIND_USER_BY_ID)
    public CustomResponse<UserDTO> findUserByIdHandler(@PathVariable String id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "User fetched Successfully",
                userService.findUserById(id)
        );
    }


    @DeleteMapping(UrlConstants.DELETE_USER)
    public CustomResponse<Void> deleteUserHandler(@PathVariable String id) {
        userService.deleteUser(id);
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "User Deleted Successfully",
                null
        );
    }
}
