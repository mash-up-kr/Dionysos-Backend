package com.dionysos.api.diary.repository;

import com.dionysos.api.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
//    List<Diary> findAllByUserId(Long user_id);
}
