package com.dionysos.api.user.controller;

import com.dionysos.api.exception.NotExistNicknameException;
import com.dionysos.api.exception.NotExistUserException;
import com.dionysos.api.user.dto.*;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<ResponseSignInDto> signIn() {
        return userService.signIn();
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseSignUpDto> signUp(@RequestBody RequestSignUpDto requestBody) {
        return userService.signUp(requestBody);
    }

    @PostMapping("/check/nickname")
    public ResponseEntity checkNickname(@RequestBody RequestNicknameCheckDto requestBody) {
        boolean result = userService.existNickname(requestBody);
        if (result == true) {
            throw new NotExistNicknameException();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/my")
    public ResponseEntity<ResponseUserDto> myProfile() {
        User user = userService.getFromUid();
        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .providerType(user.getProvider())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseUserDto);
    }

    @PutMapping("/my")
    public ResponseEntity<ResponseUserDto> changeProfile(@RequestBody ReqeustChangeNicknameDto requestBody) {
        return userService.changeProfile(requestBody);
    }

    @DeleteMapping("/signout")
    public ResponseEntity signOut() {
        userService.signOut();
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

}
