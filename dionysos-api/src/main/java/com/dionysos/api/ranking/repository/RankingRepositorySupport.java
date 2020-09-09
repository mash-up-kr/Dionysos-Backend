package com.dionysos.api.ranking.repository;

import com.dionysos.api.timehistory.entity.TimeHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.dionysos.api.timehistory.entity.QTimeHistory.timeHistory;

@Repository
public class RankingRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public RankingRepositorySupport(JPAQueryFactory queryFactory) {
        super(TimeHistory.class);
        this.queryFactory = queryFactory;
    }

    public List<TimeHistory> dayRanking(LocalDateTime begin, LocalDateTime end) {
        return queryFactory
                .selectFrom(timeHistory)
                .where(timeHistory.historyDay.between(begin, end))
                .fetch();
    }
}
