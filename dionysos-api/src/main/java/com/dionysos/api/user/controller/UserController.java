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
    private final UserMainService userMainService;
    private final JwtService jwtService;

    private static final String HEADER_AUTH = "Authorization";

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody RequestUIDDto requestBody) {

        if (userService.isExisted(requestBody.getUid())) {
            String jws = jwtService.create(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HEADER_AUTH, jws);

            return ResponseEntity.status(HttpStatus.OK).headers(headers)
                    .body(userMainService.getResponseUserDto(requestBody.getUid()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody RequestUserDto requestBody) {
        userService.signUp(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/my")
    public ResponseEntity<ResponseUserDto> myProfile(@RequestBody RequestUIDDto requestBody) {
        User user = userService.getFromUid(requestBody.getUid());
        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseUserDto);
    }

    @PutMapping("/my")
    public ResponseEntity<ResponseUserDto> changeProfile(@RequestBody RequestUserDto requestBody) {
        User user = userService.setNickname(requestBody);
        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseUserDto);
    }

    @DeleteMapping("/sign-out")
    public ResponseEntity signOut(@RequestBody RequestUserDto requestBody) {
        userService.signOut(requestBody);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

}
