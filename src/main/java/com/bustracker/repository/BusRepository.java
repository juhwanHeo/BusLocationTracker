package com.bustracker.repository;


import com.bustracker.entity.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus, String> {

}
