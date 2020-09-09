package com.dionysos.api.timehistory.repository;

import com.dionysos.api.timehistory.entity.TimeHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.dionysos.api.timehistory.entity.QTimeHistory.timeHistory;

@Repository
public class TimeHistoryRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public TimeHistoryRepositorySupport(JPAQueryFactory queryFactory) {
        super(TimeHistory.class);
        this.queryFactory = queryFactory;
    }

    public TimeHistory getTimeHistory(Long user_id, LocalDateTime begin, LocalDateTime end) {
        return queryFactory
                .selectFrom(timeHistory)
                .where(timeHistory.user.id.eq(user_id))
                .where(timeHistory.historyDay.between(begin, end))
                .orderBy(timeHistory.historyDay.desc())
                .limit(1)
                .fetchFirst();
    }

    public Long getDuration(Long user_id, LocalDateTime begin, LocalDateTime end) {
        return queryFactory
                .selectFrom(timeHistory)
                .where(timeHistory.user.id.eq(user_id))
                .where(timeHistory.historyDay.between(begin, end))
                .orderBy(timeHistory.historyDay.desc())
                .limit(1)
                .fetchFirst()
                .getDuration();
    }
}