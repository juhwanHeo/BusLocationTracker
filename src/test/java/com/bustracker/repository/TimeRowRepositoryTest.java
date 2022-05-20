package com.bustracker.repository;

import com.bustracker.entity.Time;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeTable;
import com.bustracker.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TimeRowRepositoryTest {

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Test
    public void setUp() {

        List<String> timeIdList = timeRepository.findAll().stream()
                .map(Time::getId)
                .collect(Collectors.toList());

        TimeRow timeRow = new TimeRow(null, 1, timeIdList);

        timeRowRepository.save(timeRow);
    }

    @Test
    public void findAll() {
        List<TimeRow> list = timeRowRepository.findAll();

        log.info("list: {}", list);
    }

}