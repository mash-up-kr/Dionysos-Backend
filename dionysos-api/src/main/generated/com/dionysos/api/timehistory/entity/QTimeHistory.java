package com.dionysos.api.timehistory.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTimeHistory is a Querydsl query type for TimeHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTimeHistory extends EntityPathBase<TimeHistory> {

    private static final long serialVersionUID = -1876078410L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimeHistory timeHistory = new QTimeHistory("timeHistory");

    public final NumberPath<Long> duration = createNumber("duration", Long.class);

    public final DateTimePath<java.time.LocalDateTime> historyDay = createDateTime("historyDay", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.dionysos.api.user.entity.QUser user;

    public QTimeHistory(String variable) {
        this(TimeHistory.class, forVariable(variable), INITS);
    }

    public QTimeHistory(Path<? extends TimeHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTimeHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTimeHistory(PathMetadata metadata, PathInits inits) {
        this(TimeHistory.class, metadata, inits);
    }

    public QTimeHistory(Class<? extends TimeHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.dionysos.api.user.entity.QUser(forProperty("user")) : null;
    }

}

