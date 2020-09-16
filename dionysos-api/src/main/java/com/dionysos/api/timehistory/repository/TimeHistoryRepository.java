package com.dionysos.api.timehistory.repository;

import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeHistoryRepository extends JpaRepository<TimeHistory, Long> {
}
