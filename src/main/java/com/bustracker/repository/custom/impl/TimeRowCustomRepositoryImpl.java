package com.bustracker.repository.custom.impl;

import com.bustracker.entity.TimeRow;
import com.bustracker.repository.custom.BaseCustomRepository;
import com.bustracker.repository.custom.TimeRowCustomRepository;
import com.bustracker.enums.TimeRowStatus;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TimeRowCustomRepositoryImpl extends BaseCustomRepository implements TimeRowCustomRepository {

    @Override
    public TimeRow findByCurrentTimeBetweenStartTimeAndEndTime(long currentTime) {
        Query query = new Query();
        Criteria where = new Criteria();
        where.orOperator(
                Criteria.where("status").is(TimeRowStatus.IN_PROGRESS),
                Criteria.where("startTimeMillis").gte(currentTime)
                        .and("endTimeMillis").lte(currentTime)
        );

        query.addCriteria(where);
        return mongoTemplate.findOne(query, TimeRow.class);
    }
}
