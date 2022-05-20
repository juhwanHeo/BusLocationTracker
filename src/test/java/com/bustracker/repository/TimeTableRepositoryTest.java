package com.bustracker.repository;

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

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TimeTableRepositoryTest {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Test
    public void setUp() {
        String now = DateUtils.dateToString(new Date());
        TimeTable timeTable = TimeTable.builder()
                .timeRowIds(new ArrayList<>())
                .inputDate(now)
                .updateDate(now)
                .build();

        timeTableRepository.deleteAll();
        timeTableRepository.save(timeTable);
    }

    @Test
    public void findAll() {
        List<TimeTable> list = timeTableRepository.findAll();

        log.info("list: {}", list);
    }

}