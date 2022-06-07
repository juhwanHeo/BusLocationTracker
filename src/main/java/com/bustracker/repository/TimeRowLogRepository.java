package com.bustracker.repository;


import com.bustracker.entity.TimeRowLog;
import com.bustracker.repository.custom.TimeRowLogCustomRepository;
import com.bustracker.status.TimeRowStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeRowLogRepository extends MongoRepository<TimeRowLog, String>, TimeRowLogCustomRepository {
    List<TimeRowLog> findAllByTimetableId(String timetableId);
    List<TimeRowLog> findAllByStartTimeMillisIsGreaterThanEqual(long startTime);
    List<TimeRowLog> findAllByStatusAndToday(TimeRowStatus status, String today);
    List<TimeRowLog> findByToday(String today);

}
