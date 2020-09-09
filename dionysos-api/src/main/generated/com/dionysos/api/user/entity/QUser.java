package com.dionysos.api.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 694965222L;

    public static final QUser user = new QUser("user");

    public final ListPath<com.dionysos.api.diary.entity.Diary, com.dionysos.api.diary.entity.QDiary> diaries = this.<com.dionysos.api.diary.entity.Diary, com.dionysos.api.diary.entity.QDiary>createList("diaries", com.dionysos.api.diary.entity.Diary.class, com.dionysos.api.diary.entity.QDiary.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final EnumPath<ProviderType> provider = createEnum("provider", ProviderType.class);

    public final ListPath<com.dionysos.api.timehistory.entity.TimeHistory, com.dionysos.api.timehistory.entity.QTimeHistory> timeHistoriesList = this.<com.dionysos.api.timehistory.entity.TimeHistory, com.dionysos.api.timehistory.entity.QTimeHistory>createList("timeHistoriesList", com.dionysos.api.timehistory.entity.TimeHistory.class, com.dionysos.api.timehistory.entity.QTimeHistory.class, PathInits.DIRECT2);

    public final StringPath uid = createString("uid");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

