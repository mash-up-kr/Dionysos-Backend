package com.dionysos.api.user.service;

import com.dionysos.api.auth.service.JwtService;
import com.dionysos.api.common.response.DionysosAPIResponse;
import com.dionysos.api.user.dto.*;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.exception.AlreadyExistUserException;
import com.dionysos.api.user.exception.NotExistUserException;
import com.dionysos.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    private static final String HEADER_AUTH = "Authorization";

    @Transactional(readOnly = true)
    public boolean isExisted(String uid) {
        return userRepository.findByUid(uid).isPresent();
    }

    public ResponseEntity<DionysosAPIResponse<ResponseSignInDto>> signIn(RequestSignInDto requestSignInDto) {
        String convertedUid = getConvertedUidFromUid(requestSignInDto.getUid(),
                requestSignInDto.getProvider().toString()
        );

        Optional<User> optionalUser = userRepository.findByUid(convertedUid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            ResponseSignInDto dto = ResponseSignInDto.builder()
                    .uid(user.getUid())
                    .nickname(user.getNickname())
                    .provider(user.getProvider())
                    .jwt(jwtService.create(user.getUid()))
                    .build();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(DionysosAPIResponse.<ResponseSignInDto>builder()
                            .result(dto)
                            .build()
            );
         } else {
            throw new NotExistUserException();
        }
    }

    public ResponseEntity<DionysosAPIResponse<ResponseSignUpDto>> signUp(RequestSignUpDto requestSignUpDto) {
        String convertedUid = getConvertedUidFromUid(requestSignUpDto.getUid(),
                requestSignUpDto.getProvider().toString()
        );

        if (isExisted(convertedUid)) {
            throw new AlreadyExistUserException();
        }

        User user = User.builder()
                        .uid(convertedUid)
                        .nickname(requestSignUpDto.getNickname())
                        .provider(requestSignUpDto.getProvider())
                        .build();

        userRepository.save(user);

        String jwt = jwtService.create(convertedUid);

        ResponseSignUpDto dto = ResponseSignUpDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .provider(user.getProvider())
                .jwt(jwt)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(DionysosAPIResponse.<ResponseSignUpDto>builder()
                        .result(dto)
                        .build()
                );
    }
    public ResponseEntity<DionysosAPIResponse<ResponseUserDto>> changeProfile(ReqeustChangeNicknameDto requestNicknameDto) {
        RequestSignUpDto requestUserDto = RequestSignUpDto.builder()
                .uid(jwtService.getUid())
                .nickname(requestNicknameDto.getNickname())
                .build();

        User user = setNickname(requestUserDto);
        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .provider(user.getProvider())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(DionysosAPIResponse.<ResponseUserDto>builder()
                        .result(responseUserDto)
                        .build()
                );
    }

    @Transactional(readOnly = true)
    public boolean existNickname(RequestNicknameCheckDto requestNicknameCheckDto) {
        return userRepository.findByNickname(requestNicknameCheckDto.getNickname())
                .isPresent();
    }

    @Transactional(readOnly = true)
    public User getFromUid(String uid) {
        return userRepository.findByUid(uid)
                .orElseThrow(NotExistUserException::new);
    }

    @Transactional(readOnly = true)
    public User getFromUid() {
        String uid = jwtService.getUid();
        return userRepository.findByUid(uid)
                .orElseThrow(NotExistUserException::new);

    }

    public User setNickname(RequestSignUpDto requestUserDto) {
        User user = userRepository.findByUid(requestUserDto.getUid())
                .orElseThrow(NotExistUserException::new);
        user.changeNickname(requestUserDto.getNickname());
        userRepository.save(user);
        return user;
    }

    public void signOut() {
        String uid = jwtService.getUid();
        User user = userRepository.findByUid(uid)
                .orElseThrow(NotExistUserException::new);
        userRepository.delete(user);
    }

    private String getConvertedUidFromUid(String uid,
                                          String provider
    ) {
        StringBuilder convertedUid = new StringBuilder(256);
        convertedUid.append(provider);
        convertedUid.append("_");
        convertedUid.append(uid);

        return convertedUid.toString();
    }
}
