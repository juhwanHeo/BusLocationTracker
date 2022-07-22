package com.bustracker.repository;

import com.bustracker.entity.Station;
import com.bustracker.entity.Time;
import com.bustracker.entity.TimeRow;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TimeRepositoryTest {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void setUp() {
        List<Time> list = new ArrayList<>();

        List<Station> stationList = stationRepository.findAll();

        timeRepository.deleteAll();
        timeRepository.saveAll(list);
    }

    @Test
    public void setUp2() {
        List<Time> timeList = new ArrayList<>();
        List<Station> stationList = stationRepository.findAll();
        List<TimeRow> timeRowList = timeRowRepository.findAll();
        String[][] timeArray = {
                {"07:20", "07:25", "07:30", "07:35"},
                {"07:40", "07:45", "07:50", "07:55"},
                {"08:20", "08:25", "08:30", "08:35"},
                {"08:40", "08:45", "08:50", "08:55"},
        };
        int index = 0;
//        for (TimeRow timeRow : timeRowList) {
//            timeList.add(new Time(null, timeRow.getId(), 1, stationList.get(0).getId(), timeArray[index][0], null));
//            timeList.add(new Time(null, timeRow.getId(), 2, stationList.get(1).getId(), timeArray[index][1], null));
//            timeList.add(new Time(null, timeRow.getId(), 3, stationList.get(2).getId(), timeArray[index][2], null));
//            timeList.add(new Time(null, timeRow.getId(), 4, stationList.get(0).getId(), timeArray[index][3], null));
//            timeRow.setStartTime(timeList.get(0).getTime());
//        }

        timeRowRepository.saveAll(timeRowList);
        timeRepository.deleteAll();
        timeRepository.saveAll(timeList);
    }

    @Test
    public void findAll() {
        List<Time> list = timeRepository.findAll();

        log.info("list: {}", list);
    }

}