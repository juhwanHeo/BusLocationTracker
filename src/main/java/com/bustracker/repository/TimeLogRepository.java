package com.bustracker.repository;


import com.bustracker.entity.TimeLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TimeLogRepository extends MongoRepository<TimeLog, String> {
    List<TimeLog> findByTimeRowLogId(String timeRowLogId);
}
