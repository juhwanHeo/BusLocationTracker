package com.bustracker.repository;

import com.bustracker.entity.Station;
import com.bustracker.entity.Time;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeTable;
import com.bustracker.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class TimeTableRepositoryTest {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private StationRepository stationRepository;



    @Test
    public void updateTest() {
        String now = DateUtils.dateToString(new Date());
        TimeTable timeTable = timeTableRepository.findAll().get(0);

        timeTableRepository.save(timeTable);
    }

    @Test
    public void findAll() {
        List<TimeTable> list = timeTableRepository.findAll();

        log.info("list: {}", list);
        log.info("LocalTime.now(): {}", LocalTime.now());
    }

}