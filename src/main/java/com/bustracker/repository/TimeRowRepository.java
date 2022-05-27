package com.bustracker.repository;


import com.bustracker.entity.TimeRow;
import com.bustracker.repository.custom.TimeRowCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeRowRepository extends MongoRepository<TimeRow, String>, TimeRowCustomRepository {
    List<TimeRow> findAllByTimetableId(String timetableId);
    List<TimeRow> findByStartTimeMillisIsGreaterThanEqual(long startTime);
}
