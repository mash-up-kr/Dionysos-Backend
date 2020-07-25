package com.dionysos.api.user.controller;

import com.dionysos.api.user.dto.*;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.service.JwtService;
import com.dionysos.api.user.service.UserMainService;
import com.dionysos.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<ResponseUserDto> signIn(@RequestBody RequestUIDDto requestBody) {
        return userService.signIn(requestBody);
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody RequestUserDto requestBody) {
        userService.signUp(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/my")
    public ResponseEntity<ResponseUserDto> myProfile() {
        User user = userService.getFromUid();
        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseUserDto);
    }

    @PutMapping("/my")
    public ResponseEntity<ResponseUserDto> changeProfile(@RequestBody RequestNicknameDto requestBody) {
        return userService.changeProfile(requestBody);
    }

    @DeleteMapping("/signout")
    public ResponseEntity signOut(@RequestBody RequestUIDDto requestBody) {
        userService.signOut(requestBody);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

}
