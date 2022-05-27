package com.bustracker.repository.custom.impl;

import com.bustracker.entity.TimeRowLog;
import com.bustracker.repository.custom.BaseCustomRepository;
import com.bustracker.repository.custom.TimeRowLogCustomRepository;
import com.bustracker.status.TimeRowStatus;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TimeRowLogCustomRepositoryImpl extends BaseCustomRepository implements TimeRowLogCustomRepository {

    @Override
    public TimeRowLog findByCurrentTimeBetweenStartTimeAndEndTime(long currentTime) {

        Query query = new Query();
        Criteria where = Criteria.where("status").is(TimeRowStatus.IN_PROGRESS);
        where.orOperator(
                Criteria.where("startTimeMillis").gte(currentTime)
                        .and("endTimeMillis").lte(currentTime)
        );

        query.addCriteria(where);

        return mongoTemplate.findOne(query, TimeRowLog.class);
    }
}
