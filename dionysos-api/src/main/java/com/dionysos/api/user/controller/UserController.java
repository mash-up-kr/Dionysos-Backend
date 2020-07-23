package com.dionysos.api.user.controller;

import com.dionysos.api.user.dto.RequestSignUpDto;
import com.dionysos.api.user.dto.RequestUserDto;
import com.dionysos.api.user.service.JwtService;
import com.dionysos.api.user.service.UserMainService;
import com.dionysos.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMainService userMainService;

    @Autowired
    private JwtService jwtService;

    private static final String HEADER_AUTH = "Authorization";

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody RequestUserDto requestBody) {

        // TODO: 닉네임 중복 확인하는지 체크하기
        if (userService.isExisted(requestBody.getUid(), requestBody.getNickname())) {
            String jws = jwtService.create(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HEADER_AUTH, jws);

            return ResponseEntity.status(HttpStatus.OK).headers(headers)
                    .body(userMainService.getResponseUserDto(requestBody.getUid()));
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody RequestSignUpDto requestBody) {
        userService.signUp(requestBody);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
