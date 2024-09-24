package dev.anirban.kritique.controller;

import dev.anirban.kritique.constants.NetworkStatusCodes;
import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.common.AccessTokenBody;
import dev.anirban.kritique.dto.common.CustomResponse;
import dev.anirban.kritique.dto.user.UserDTO;
import dev.anirban.kritique.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping(UrlConstants.LOGIN_USER)
    public CustomResponse<UserDTO> loginUserHandler(@RequestBody AccessTokenBody tokenBody) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "User Logged Successfully",
                service.loginUser(tokenBody)
        );
    }
}
