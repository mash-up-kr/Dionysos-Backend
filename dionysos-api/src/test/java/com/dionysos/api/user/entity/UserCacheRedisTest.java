package com.dionysos.api.user.entity;

import com.dionysos.api.user.repository.UserCacheRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCacheRedisTest {

    @Autowired
    private UserCacheRepository userCacheRepository;

    @After
    public void tearDown() throws Exception {
        userCacheRepository.deleteAll();
    }

    @Test
    public void 기본_등록_조회기능() {
        //given
        Long id = 1L;
        String uid = "kakao_chipmunk";
        String nickname = "chipmunk";
        LocalDateTime refreshTime = LocalDateTime.of(2020, 9, 10, 0, 0);

        UserCache userCache = UserCache.builder()
                .id(id)
                .uid(uid)
                .nickname(nickname)
                .refreshTime(refreshTime)
                .build();

        //when
        userCacheRepository.save(userCache);

        //then
        UserCache savedUserCache = userCacheRepository.findById(id).get();
        assertThat(savedUserCache.getUid()).isEqualTo(uid);
        assertThat(savedUserCache.getNickname()).isEqualTo(nickname);
        assertThat(savedUserCache.getRefreshTime()).isEqualTo(refreshTime);
    }

    @Test
    public void 수정기능() {
        //given
        Long id = 1L;
        String uid = "kakao_chipmunk";
        String nickname = "chipmunk";
        LocalDateTime refreshTime = LocalDateTime.of(2020, 9, 10, 0, 0);
        userCacheRepository.save(UserCache.builder()
                .id(id)
                .uid(uid)
                .nickname(nickname)
                .refreshTime(refreshTime)
                .build());

        //when
        UserCache savedPoint = userCacheRepository.findById(id).get();
        savedPoint.refresh(LocalDateTime.of(2020,9,10,0,5));
        userCacheRepository.save(savedPoint);

        //then
        UserCache refreshPoint = userCacheRepository.findById(id).get();
        assertThat(refreshPoint.getRefreshTime()).isEqualTo(LocalDateTime.of(2020,9,10,0,5));
    }
}