package com.dionysos.api.timehistory.repository;

import com.dionysos.api.timehistory.entity.RunningUser;
import org.springframework.data.repository.CrudRepository;

public interface RunningUserRedisRepository extends CrudRepository<RunningUser, Long> {
}
