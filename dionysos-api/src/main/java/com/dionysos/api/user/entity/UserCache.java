package com.dionysos.api.user.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RedisHash("user")
public class UserCache implements Serializable {

    @Id
    private Long id;
    private String uid;
    private String nickname;

    private LocalDateTime refreshTime;

    private static final Long timeToLiveMinute = 5L;

    @Builder
    public UserCache(Long id, String uid, String nickname, LocalDateTime refreshTime) {
        this.id = id;
        this.uid = uid;
        this.nickname = nickname;
        this.refreshTime = refreshTime;
    }

    public void refresh(LocalDateTime refreshTime) {
        if (refreshTime.isAfter(this.refreshTime.plusMinutes(timeToLiveMinute - 1).plusSeconds(59))) {
            this.refreshTime = refreshTime;
        }
    }

}
