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
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Test
    public void setUp() {
        TimeTable timetable = timeTableRepository.findAll().get(0);
        List<TimeRow> timeRowList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TimeRow timeRow = TimeRow.builder()
                    .timetableId(timetable.getId())
                    .order(i + 1)
                    .build();

            timeRowList.add(timeRow);
        }

        timeRowRepository.saveAll(timeRowList);
    }

    @Test
    public void updateTest() {
        List<String> timeIdList = timeRepository.findAll().stream()
                .map(Time::getId)
                .collect(Collectors.toList());

        TimeRow timeRow = timeRowRepository.findAll().get(0);

        timeRowRepository.save(timeRow);
    }

    @Test
    public void findAll() {
        List<TimeRow> list = timeRowRepository.findAll();

        log.info("list: {}", list);
    }

}