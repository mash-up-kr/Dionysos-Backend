package com.dionysos.api.timehistory.entity;


import com.dionysos.api.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TimeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime historyDay;
    private Long duration;
    private boolean isRunning;

    @ManyToOne
    @JoinColumn
    public User user;

    @Builder
    private TimeHistory(LocalDateTime historyDay,
                        User user,
                        Long duration
    ) {
        this.historyDay = historyDay;
        this.user = user;
        this.duration = duration;
        this.isRunning = true;
    }

    public void update(Long duration,
                       LocalDateTime historyDay
    ) {
        this.duration = duration;
        this.historyDay = historyDay;
    }

}
