package com.bustracker.repository;


import com.bustracker.entity.TimeRowLog;
import com.bustracker.repository.custom.TimeRowLogCustomRepository;
import com.bustracker.status.TimeRowStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeRowLogRepository extends MongoRepository<TimeRowLog, String>, TimeRowLogCustomRepository {
    List<TimeRowLog> findAllByTimetableId(String timetableId);
    List<TimeRowLog> findAllByStartTimeMillisIsGreaterThanEqual(long startTime);
    List<TimeRowLog> findAllByStatusAndRunningDate(TimeRowStatus status, String runningDate);
    List<TimeRowLog> findByRunningDate(String today);
    boolean existsByRunningDate(String runningDate);


}
