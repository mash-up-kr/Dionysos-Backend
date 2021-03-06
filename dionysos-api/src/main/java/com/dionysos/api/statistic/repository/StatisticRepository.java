package com.dionysos.api.statistic.repository;

import com.dionysos.api.timehistory.entity.TimeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticRepository extends JpaRepository<TimeHistory, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM TIME_HISTORY t " +
                    "WHERE t.USER_ID = ?1 " +
                    "AND (t.HISTORY_DAY BETWEEN ?2 AND ?3) " +
                    "ORDER BY t.HISTORY_DAY ")
    List<TimeHistory> getHistoryDayAndDuration(Long user_id, LocalDateTime begin, LocalDateTime end);

}
