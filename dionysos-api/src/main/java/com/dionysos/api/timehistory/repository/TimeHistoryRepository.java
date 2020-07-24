package com.dionysos.api.timehistory.repository;

import com.dionysos.api.timehistory.entity.TimeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeHistoryRepository extends JpaRepository<TimeHistory, Long> {
}
