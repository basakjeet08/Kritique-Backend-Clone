package dev.anirban.kritique.controller;

import dev.anirban.kritique.constants.NetworkStatusCodes;
import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.common.CustomResponse;
import dev.anirban.kritique.dto.common.EmptyObject;
import dev.anirban.kritique.dto.user.UserDTO;
import dev.anirban.kritique.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(UrlConstants.CREATE_USER)
    public CustomResponse<UserDTO> createUserHandler(@RequestBody UserDTO user) {
        return new CustomResponse<>(
                NetworkStatusCodes.CREATED,
                "User Created Successfully",
                userService.createUser(user)
        );
    }

    @GetMapping(UrlConstants.FIND_ALL_USERS)
    public CustomResponse<List<UserDTO>> findAllUserHandler() {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "Users fetched successfully",
                userService.findAllUsers()
        );
    }

    @GetMapping(UrlConstants.FIND_USER_BY_ID)
    public CustomResponse<UserDTO> findUserByIdHandler(@PathVariable String id) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "User fetched Successfully",
                userService.findUserById(id)
        );
    }


    @DeleteMapping(UrlConstants.DELETE_USER)
    public CustomResponse<EmptyObject> deleteUserHandler(@PathVariable String id) {
        userService.deleteUser(id);
        return new CustomResponse<>(
                NetworkStatusCodes.DELETED,
                "User Deleted Successfully",
                new EmptyObject()
        );
    }
}
