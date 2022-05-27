package com.bustracker.repository;


import com.bustracker.entity.TimeRowLog;
import com.bustracker.repository.custom.TimeRowLogCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeRowLogRepository extends MongoRepository<TimeRowLog, String>, TimeRowLogCustomRepository {
    List<TimeRowLog> findAllByTimetableId(String timetableId);
    List<TimeRowLog> findByStartTimeMillisIsGreaterThanEqual(long startTime);
}
