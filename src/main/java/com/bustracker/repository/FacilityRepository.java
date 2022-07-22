package com.bustracker.repository;


import com.bustracker.entity.Facility;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacilityRepository extends MongoRepository<Facility, String> {

}
