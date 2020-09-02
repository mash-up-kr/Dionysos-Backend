package com.dionysos.api.timehistory.repository;

import com.dionysos.api.timehistory.dto.ResponseRankingWeekMonthDto;
import com.dionysos.api.timehistory.entity.TimeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TimeHistoryRepository extends JpaRepository<TimeHistory, Long> {
    @Query(nativeQuery = true, value = "select * from Time_History t WHERE t.USER_ID = ?1 AND t.HISTORY_DAY BETWEEN ?2 AND ?3 ORDER BY t.HISTORY_DAY desc limit 1")
    List<TimeHistory> getTimeHistory(Long user_id, LocalDateTime begin, LocalDateTime end);

    @Query(nativeQuery = true, value = "select * from TIME_HISTORY t WHERE t.USER_ID = ?1 AND (t.HISTORY_DAY BETWEEN ?2 AND ?3) ORDER BY t.HISTORY_DAY desc limit 1")
    Optional<TimeHistory> getDuration(Long user_id, LocalDateTime begin, LocalDateTime end);

    @Query(nativeQuery = true, value = "select * from TIME_HISTORY t WHERE t.USER_ID = ?1 ORDER BY t.HISTORY_DAY desc limit 1")
    Optional<TimeHistory> getLastDay(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM TIME_HISTORY t WHERE t.HISTORY_DAY BETWEEN ?1 AND ?2 ORDER BY t.DURATION DESC")
    List<TimeHistory> dayRanking(LocalDateTime begin, LocalDateTime end);

    //주간, 월간은 GROUP BY user로
    @Query(nativeQuery = true, value = "SELECT t.USER_ID AS ID, SUM(t.DURATION) AS DURATION FROM TIME_HISTORY t WHERE t.HISTORY_DAY BETWEEN ?1 AND ?2 " +
            "GROUP BY t.USER_ID ORDER BY SUM(t.DURATION) DESC")
    List<ResponseRankingWeekMonthDto> weeklyMonthlyRanking(LocalDateTime begin, LocalDateTime end);

}
