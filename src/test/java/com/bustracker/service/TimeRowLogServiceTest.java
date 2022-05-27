package com.bustracker.service;

import com.bustracker.entity.TimeRowLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TimeRowLogServiceTest {

    @Autowired
    private TimeRowLogService timeRowLogService;

    /*
    *
    *
   db.getCollection('c_time_row').find(
    {$or: [
        {'status': 'IN_PROGRESS'},
        {
           $and: [
                {'startTimeMillis': { $gte: 63663093000000 }},
                {'endTimeMillis': { $lte: 63663093000000 }}
            ]
        }]
    })
    * */
    @Test
    void findCurrentTimeRow() {
        TimeRowLog timeRow = timeRowLogService.findCurrentTimeRow();

        LocalTime time =  LocalTime.parse("15:30");
        log.info("current: {}", LocalTime.now());
        log.info("time.hour: {}", time.getHour());
        log.info("time.minus: {}", time.getMinute());


        log.info("long current: {}", LocalTime.now().toNanoOfDay());
        log.info("long time: {}", time.toNanoOfDay());

        log.info("timeRow: {}", timeRow);
    }
}