package com.dionysos.api.user.controller;

import com.dionysos.api.common.response.DionysosAPIResponse;
import com.dionysos.api.user.exception.AlreadyExistNicknameException;
import com.dionysos.api.user.dto.*;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<DionysosAPIResponse<ResponseSignInDto>> signIn(@RequestBody RequestSignInDto requestBody) {
        return userService.signIn(requestBody);
    }

    @PostMapping("/signup")
    public ResponseEntity<DionysosAPIResponse<ResponseSignUpDto>> signUp(@RequestBody RequestSignUpDto requestBody) {
        return userService.signUp(requestBody);
    }

    @PostMapping("/check/nickname")
    public ResponseEntity<DionysosAPIResponse<Boolean>> checkNickname(@RequestBody RequestNicknameCheckDto requestBody) {
        boolean result = userService.existNickname(requestBody);
        if (result == true) {
            throw new AlreadyExistNicknameException();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(DionysosAPIResponse.<Boolean>builder().result(true).build());
    }

    @GetMapping("/my")
    public ResponseEntity<DionysosAPIResponse<ResponseUserDto>> myProfile() {
        User user = userService.getFromUid();
        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .provider(user.getProvider())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(DionysosAPIResponse.<ResponseUserDto>builder().result(responseUserDto).build());
    }

    @PutMapping("/my")
    public ResponseEntity<DionysosAPIResponse<ResponseUserDto>> changeProfile(@RequestBody ReqeustChangeNicknameDto requestBody) {
        return userService.changeProfile(requestBody);
    }

    @DeleteMapping("/signout")
    public ResponseEntity<DionysosAPIResponse<Boolean>> signOut() {
        userService.signOut();
        return ResponseEntity.status(HttpStatus.OK)
                .body(DionysosAPIResponse.<Boolean>builder().result(true).build());
    }

}
