package com.dionysos.api.user.repository;

import com.dionysos.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUid(String uid);
    Optional<User> findByNickname(String nickname);
    User findById(Long id);
}
