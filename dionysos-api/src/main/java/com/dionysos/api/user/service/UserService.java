package com.dionysos.api.user.service;

import com.dionysos.api.exception.BadRequestException;
import com.dionysos.api.user.dto.RequestSignUpDto;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public boolean isExisted(String uid, String nickname) {
        return userRepository.findById(uid).filter(u -> u.getNickname().equals(nickname)).isPresent();
    }

    @Transactional
    public void signUp(RequestSignUpDto requestSignUpDto) {
        if (isExisted(requestSignUpDto.getUid(), requestSignUpDto.getNickname()))
            throw new BadRequestException("이미 가입한 회원입니다.");

        User user = User.from(requestSignUpDto);
    }

    public User getFromUid(String uid) {
        return userRepository.findById(uid).get();
    }
}
