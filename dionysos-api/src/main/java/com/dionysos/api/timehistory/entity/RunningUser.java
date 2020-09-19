package com.dionysos.api.timehistory.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash(value = "running", timeToLive = 60*5)
public class RunningUser implements Serializable {

    @Id
    private final Long userId;

    private final Long duration;

    @Builder
    public RunningUser(Long userId, Long duration) {
        this.userId = userId;
        this.duration = duration;
    }
}
