package com.bustracker.repository;


import com.bustracker.entity.Time;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeRepository extends MongoRepository<Time, String> {

}
