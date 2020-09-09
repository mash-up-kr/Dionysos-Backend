package com.dionysos.api.timehistory.repository;

import com.dionysos.api.timehistory.entity.TimeHistory;
import com.dionysos.api.user.entity.ProviderType;
import com.dionysos.api.user.entity.User;
import com.dionysos.api.user.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static com.dionysos.api.common.util.DateUtil.stndHr;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeHistoryRepositorySupportTest {

    @Autowired
    private TimeHistoryRepository timeHistoryRepository;

    @Autowired
    private TimeHistoryRepositorySupport timeHistoryRepositorySupport;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        timeHistoryRepository.deleteAllInBatch();
        userRepository.deleteAll();
    }

    @Test
    public void 시간기록_최근기록_하나_당일_테스트() {
        //given
        User user = User.builder()
                .uid("kakao_chipmunk")
                .nickname("chipmunk")
                .provider(ProviderType.KAKAO)
                .build();

        userRepository.save(user);

        LocalDateTime historyDay = LocalDateTime.of(2020, 9, 9, 13,0,0);
        Long duration = 10L;

        timeHistoryRepository.save(TimeHistory.builder()
                .user(user)
                .historyDay(historyDay)
                .duration(duration)
                .build());

        //when
        LocalDateTime now = LocalDateTime.of(2020, 9, 9, 21,0,0);

        LocalDateTime begin = now.withHour(stndHr);

        if (now.isBefore(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), stndHr, 0, 0))) {
            begin = now.minusDays(1).withHour(stndHr);
        }

        LocalDateTime end = now.withHour(stndHr - 1).withMinute(59).withSecond(59);

        if (now.isAfter(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), stndHr, 0, 0))) {
            end = now.plusDays(1);
        }

        TimeHistory result = timeHistoryRepositorySupport.getTimeHistory(user.getId(), begin, end);

        //then
        assertThat(begin, is(LocalDateTime.of(2020, 9, 9, 6, 0, 0)));
        assertThat(end, is(LocalDateTime.of(2020, 9, 10, 5, 59, 59)));
    }


    @Test
    public void 시간기록_최근기록_하나_새벽_테스트() {
        //given
        User user = User.builder()
                .uid("kakao_chipmunk")
                .nickname("chipmunk")
                .provider(ProviderType.KAKAO)
                .build();

        userRepository.save(user);

        LocalDateTime historyDay = LocalDateTime.of(2020, 9, 10, 1,0,0);
        Long duration = 10L;

        timeHistoryRepository.save(TimeHistory.builder()
                .user(user)
                .historyDay(historyDay)
                .duration(duration)
                .build());

        //when
        LocalDateTime now = LocalDateTime.of(2020, 9, 10, 5,0,0);

        LocalDateTime begin = now.withHour(stndHr);

        if (now.isBefore(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), stndHr, 0, 0))) {
            begin = now.minusDays(1).withHour(stndHr);
        }

        LocalDateTime end = now.withHour(stndHr - 1).withMinute(59).withSecond(59);

        if (now.isAfter(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), stndHr, 0, 0))) {
            end = now.plusDays(1);
        }

        TimeHistory result = timeHistoryRepositorySupport.getTimeHistory(user.getId(), begin, end);

        //then
        assertThat(begin, is(LocalDateTime.of(2020, 9, 9, 6, 0, 0)));
        assertThat(end, is(LocalDateTime.of(2020, 9, 10, 5, 59, 59)));
    }

    @Test
    public void 시간기록_최근기록_여러개_테스트() {
        //given
        User user = User.builder()
                .uid("kakao_chipmunk")
                .nickname("chipmunk")
                .provider(ProviderType.KAKAO)
                .build();

        userRepository.save(user);

        LocalDateTime historyDay = LocalDateTime.of(2020, 9, 10, 0,0,0);
        Long duration = 10L;

        timeHistoryRepository.save(TimeHistory.builder()
                .user(user)
                .historyDay(historyDay)
                .duration(duration)
                .build());

        //when
        LocalDateTime now = LocalDateTime.of(2020, 9, 10, 5,0,0);

        LocalDateTime begin = now.withHour(stndHr);

        if (now.isBefore(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), stndHr, 0, 0))) {
            begin = now.minusDays(1).withHour(stndHr);
        }

        LocalDateTime end = now.withHour(stndHr - 1).withMinute(59).withSecond(59);

        if (now.isAfter(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), stndHr, 0, 0))) {
            end = now.plusDays(1);
        }

        TimeHistory result = timeHistoryRepositorySupport.getTimeHistory(user.getId(), begin, end);

        //then
        assertThat(begin, is(LocalDateTime.of(2020, 9, 9, 6, 0, 0)));
        assertThat(end, is(LocalDateTime.of(2020, 9, 10, 5, 59, 59)));
    }
}