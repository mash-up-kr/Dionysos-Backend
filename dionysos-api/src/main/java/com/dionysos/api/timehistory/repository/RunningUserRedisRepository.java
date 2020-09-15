package com.dionysos.api.timehistory.repository;

import com.dionysos.api.timehistory.entity.RunningUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RunningUserRedisRepository extends CrudRepository<RunningUser, Long> {
    List<RunningUser> findAll();
}
