package com.bustracker.repository;

import com.bustracker.entity.DayOff;
import com.bustracker.repository.custom.DayOffCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DayOffRepository extends MongoRepository<DayOff, String>, DayOffCustomRepository {

}
