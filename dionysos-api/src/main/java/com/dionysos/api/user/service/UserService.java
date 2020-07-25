package com.dionysos.api.user.service;

import com.dionysos.api.exception.BadRequestException;
import com.dionysos.api.exception.NotExistUserException;
import com.dionysos.api.user.dto.RequestUIDDto;
import com.dionysos.api.user.dto.RequestUserDto;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean isExisted(String uid) {
        return userRepository.findByUid(uid).isPresent();
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

    @Transactional(readOnly = true)
    public User getFromUid(String uid) {
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
