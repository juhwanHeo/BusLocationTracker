package com.bustracker.repository;


import com.bustracker.entity.Bus;
import com.bustracker.repository.custom.BusCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusRepository extends MongoRepository<Bus, String>, BusCustomRepository {

}
