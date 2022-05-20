package com.bustracker.repository;


import com.bustracker.entity.Station;
import com.bustracker.repository.custom.StationCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationRepository extends MongoRepository<Station, String>, StationCustomRepository {

}
