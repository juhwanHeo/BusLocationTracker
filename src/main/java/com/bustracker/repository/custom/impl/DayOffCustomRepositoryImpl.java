package com.bustracker.repository.custom.impl;

import com.bustracker.entity.DayOff;
import com.bustracker.repository.custom.BaseCustomRepository;
import com.bustracker.repository.custom.DayOffCustomRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class DayOffCustomRepositoryImpl extends BaseCustomRepository implements DayOffCustomRepository {

    @Override
    public List<DayOff> findAllByBetweenDate(String date) {
        Query query = new Query();
        Criteria where = Criteria.where("startDate").gte(date).and("endDate").lte(date);
        query.addCriteria(where);
        query.with(Sort.by(Sort.Direction.ASC, "startDate"));
        return mongoTemplate.find(query, DayOff.class);
    }
}
