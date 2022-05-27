package com.bustracker.repository.custom;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class BaseCustomRepository {

    @Autowired
    protected MongoTemplate mongoTemplate;

}
