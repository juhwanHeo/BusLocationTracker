package com.bustracker.repository;

import com.bustracker.entity.Station;
import com.bustracker.entity.Time;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TimeRepositoryTest {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void setUp() {
        List<Time> list = new ArrayList<>();

        List<Station> stationList = stationRepository.findAll();

        list.add(new Time(null, 1, stationList.get(0).getId(),("07:20")));
        list.add(new Time(null, 2, stationList.get(1).getId(),("07:25")));
        list.add(new Time(null, 3, stationList.get(2).getId(),("07:30")));
        list.add(new Time(null, 4, stationList.get(0).getId(),("07:35")));

        timeRepository.deleteAll();
        timeRepository.saveAll(list);
    }

    @Test
    public void findAll() {
        List<Time> list = timeRepository.findAll();

        log.info("list: {}", list);
    }

}