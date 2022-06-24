package com.bustracker.repository;

import com.bustracker.entity.Station;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class StationRepositoryTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void setUp() {
        List<Station> list = new ArrayList<>();
        Station station1 = new Station(null, "S001",  "서희아파트", 37.660935, 127.32249);
        Station station2 = new Station(null, "S002", "마석역 1번출구", 37.652059, 127.311561);
        Station station3 = new Station(null, "S003", "마석역 2번출구", 37.652995, 127.311207);
        Station station4 = new Station(null, "S004", "심석 중.고", 37.656376, 127.305985);
        Station station5 = new Station(null, "S005", "송라 초.중", 37.655009, 127.302257);
        Station station7 = new Station(null, "S006", "마석 초.중", 37.648726, 127.305006);
        Station station8 = new Station(null, "S007", "마석고", 37.644891, 127.300135);

        list.add(station1);
        list.add(station2);
        list.add(station3);
        list.add(station4);
        list.add(station5);
        list.add(station7);
        list.add(station8);

        stationRepository.deleteAll();
        stationRepository.insert(list);
    }

    @Test
    public void findAll() {
        List<Station> stations = stationRepository.findAll();
        log.info("stations: {}", stations);
    }

}