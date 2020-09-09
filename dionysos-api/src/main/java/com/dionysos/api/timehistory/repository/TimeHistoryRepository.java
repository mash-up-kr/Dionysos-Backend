package com.dionysos.api.timehistory.repository;

import com.dionysos.api.timehistory.entity.TimeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TimeHistoryRepository extends JpaRepository<TimeHistory, Long> {
    @Query(nativeQuery = true, value = "select * from Time_History t WHERE t.USER_ID = ?1 AND t.HISTORY_DAY BETWEEN ?2 AND ?3 ORDER BY t.HISTORY_DAY desc limit 1")
    Optional<TimeHistory> getTimeHistory(Long user_id, LocalDateTime begin, LocalDateTime end);

    @Query(nativeQuery = true, value = "select * from TIME_HISTORY t WHERE t.USER_ID = ?1 AND (t.HISTORY_DAY BETWEEN ?2 AND ?3) ORDER BY t.HISTORY_DAY desc limit 1")
    Optional<TimeHistory> getDuration(Long user_id, LocalDateTime begin, LocalDateTime end);
}
