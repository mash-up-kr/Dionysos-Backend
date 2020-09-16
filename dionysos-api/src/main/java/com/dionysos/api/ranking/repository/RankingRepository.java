package com.dionysos.api.ranking.repository;

import com.dionysos.api.ranking.dto.ResponseRankingDtoImpl;
import com.dionysos.api.timehistory.entity.TimeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<TimeHistory, Long> {
    @Query(nativeQuery = true, value = "select * from TIME_HISTORY t WHERE t.USER_ID = ?1 ORDER BY t.HISTORY_DAY desc limit 1")
    Optional<TimeHistory> getLastDay(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM TIME_HISTORY t WHERE t.HISTORY_DAY BETWEEN ?1 AND ?2 ORDER BY t.DURATION DESC")
    List<TimeHistory> dayRanking(LocalDateTime begin, LocalDateTime end);

    @Query(nativeQuery = true, value = "SELECT t.USER_ID AS ID, SUM(t.DURATION) AS DURATION FROM TIME_HISTORY t WHERE t.HISTORY_DAY BETWEEN ?1 AND ?2 " +
            "GROUP BY t.USER_ID ORDER BY SUM(t.DURATION) DESC")
    List<ResponseRankingDtoImpl> weeklyMonthlyRanking(LocalDateTime begin, LocalDateTime end);
}
