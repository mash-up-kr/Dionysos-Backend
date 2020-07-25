package com.dionysos.api.user.service;

import com.dionysos.api.exception.BadRequestException;
import com.dionysos.api.exception.NotExistUserException;
import com.dionysos.api.user.dto.RequestNicknameDto;
import com.dionysos.api.user.dto.RequestUIDDto;
import com.dionysos.api.user.dto.RequestUserDto;
import com.dionysos.api.user.dto.ResponseUserDto;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class    UserService {

    private final UserRepository userRepository;
    private final UserMainService userMainService;
    private final JwtService jwtService;

    private static final String HEADER_AUTH = "Authorization";

    @Transactional(readOnly = true)
    public boolean isExisted(String uid) {
        return userRepository.findByUid(uid).isPresent();
    }

    public ResponseEntity<ResponseUserDto> signIn(RequestUIDDto requestUIDDto) {
        if (isExisted(requestUIDDto.getUid())) {
            String jws = jwtService.create(requestUIDDto);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HEADER_AUTH, jws);

            return ResponseEntity.status(HttpStatus.OK).headers(headers)
                    .body(userMainService.getResponseUserDto(requestUIDDto.getUid()));
         } else {
            throw new NotExistUserException();
        }
    }

    public void signUp(RequestUserDto requestSignUpDto) {
        if (isExisted(requestSignUpDto.getUid()))
            throw new BadRequestException("이미 가입한 회원입니다.");

        User user = User.builder()
                        .uid(requestSignUpDto.getUid())
                        .nickname(requestSignUpDto.getNickname())
                        .build();

        userRepository.save(user);
    }

    public ResponseEntity changeProfile(RequestNicknameDto requestNicknameDto) {
        RequestUserDto requestUserDto = RequestUserDto.builder()
                .uid(jwtService.getUid())
                .nickname(requestNicknameDto.getNickname())
                .build();

        User user = setNickname(requestUserDto);
        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseUserDto);
    }

    @Transactional(readOnly = true)
    public User getFromUid(String uid) {
        return userRepository.findByUid(uid).orElseThrow(NotExistUserException::new);
    }

    @Transactional(readOnly = true)
    public User getFromUid() {
        String uid = jwtService.getUid();
        return userRepository.findByUid(uid).orElseThrow(NotExistUserException::new);

    }

    public User setNickname(RequestUserDto requestUserDto) {
        User user = userRepository.findByUid(requestUserDto.getUid()).orElseThrow(NotExistUserException::new);
        user.changeNickname(requestUserDto.getNickname());
        userRepository.save(user);
        return user;
    }

    public void signOut(RequestUIDDto requestBody) {
        User user = userRepository.findByUid(requestBody.getUid()).orElseThrow(NotExistUserException::new);
        userRepository.delete(user);
    }
}
