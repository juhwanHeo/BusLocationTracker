package com.bustracker.repository;


import com.bustracker.entity.TimeRow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeRowRepository extends MongoRepository<TimeRow, String> {

}
