package com.bustracker.repository;


import com.bustracker.entity.TimeTable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeTableRepository extends MongoRepository<TimeTable, String> {

}
