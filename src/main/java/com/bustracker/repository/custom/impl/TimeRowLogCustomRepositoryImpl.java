package com.bustracker.repository.custom.impl;

import com.bustracker.entity.TimeRowLog;
import com.bustracker.repository.custom.BaseCustomRepository;
import com.bustracker.repository.custom.TimeRowLogCustomRepository;
import com.bustracker.status.TimeRowStatus;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeRowLogCustomRepositoryImpl extends BaseCustomRepository implements TimeRowLogCustomRepository {

    @Override
    public TimeRowLog findRunning(long currentTime) {
        Query query = new Query();
        Criteria where = new Criteria();
        where.orOperator(
                Criteria.where("status").is(TimeRowStatus.IN_PROGRESS),
                Criteria.where("today").is(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                        .and("startTimeMillis").gte(currentTime)
                        .and("endTimeMillis").lte(currentTime)
        );


        query.with(Sort.by(Sort.Direction.ASC, "today"));
        query.addCriteria(where);
        return mongoTemplate.find(query, TimeRowLog.class).stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Not Running !!"));
    }

    public TimeRowLog findByStatusAndToday(TimeRowStatus timeRowStatus) {
//        Query query = new Query();
//        Criteria where = new Criteria();
//        where.orOperator(
//                Criteria.where("status").is(TimeRowStatus.IN_PROGRESS),
//                Criteria.where("today").is(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
//                        .and("startTimeMillis").gte(currentTime)
//                        .and("endTimeMillis").lte(currentTime)
//        );
//
//        query.addCriteria(where);
//        return mongoTemplate.findOne(query, TimeRowLog.class);
        return null;
    }
}
