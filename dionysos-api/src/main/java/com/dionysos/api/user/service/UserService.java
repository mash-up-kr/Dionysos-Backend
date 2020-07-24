package com.dionysos.api.user.service;

import com.dionysos.api.exception.BadRequestException;
import com.dionysos.api.exception.NotExistUserException;
import com.dionysos.api.user.dto.RequestUserDto;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public boolean isExisted(String uid) {
        return userRepository.findByUid(uid).isPresent();
    }

    @Transactional
    public void signUp(RequestUserDto requestSignUpDto) {
        if (isExisted(requestSignUpDto.getUid()))
            throw new BadRequestException("이미 가입한 회원입니다.");

        User user = User.builder()
                        .uid(requestSignUpDto.getUid())
                        .nickname(requestSignUpDto.getNickname())
                        .build();

        userRepository.save(user);
    }

    public User getFromUid(String uid) {
        return userRepository.findByUid(uid).orElseThrow(NotExistUserException::new);
    }

    @Transactional
    public User setNickname(RequestUserDto requestUserDto) {
        User user = userRepository.findByUid(requestUserDto.getUid()).orElseThrow(NotExistUserException::new);
        user.changeNickname(requestUserDto.getNickname());
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void signOut(RequestUserDto requestBody) {
        User user = userRepository.findByUid(requestBody.getUid()).orElseThrow(NotExistUserException::new);
        userRepository.delete(user);
    }
}
