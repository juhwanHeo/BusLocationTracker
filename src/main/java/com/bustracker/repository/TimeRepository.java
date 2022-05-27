package com.bustracker.repository;


import com.bustracker.entity.Time;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeRepository extends MongoRepository<Time, String> {
    List<Time> findAllByTimeRowId(String timeRowId);
}
