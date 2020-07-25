package com.dionysos.api.timehistory.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime historyDay;
    private Long duration;
    private boolean isRunning;

    @Builder
    public TimeHistory(LocalDateTime historyDay) {
        this.historyDay = historyDay;
        this.isRunning = true;
    }

    public void update(Long duration) {
        this.duration = duration;
        this.isRunning = false;
    }
}
