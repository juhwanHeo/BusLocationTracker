package com.bustracker.repository.custom.impl;

import com.bustracker.entity.Bus;
import com.bustracker.repository.custom.BaseCustomRepository;
import com.bustracker.repository.custom.BusCustomRepository;
import com.bustracker.status.BusStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class BusCustomRepositoryImpl extends BaseCustomRepository implements BusCustomRepository {

    @Override
    public List<Bus> findRunning() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(BusStatus.IN_PROGRESS));
        query.with(Sort.by(Sort.Direction.ASC, "date"));
        return mongoTemplate.find(query, Bus.class);
    }
}
