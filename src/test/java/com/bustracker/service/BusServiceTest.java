package com.bustracker.service;

import com.bustracker.entity.Bus;
import com.bustracker.exception.ExistsTimeRowLogException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BusServiceTest {

    @Autowired
    private BusService busService;

    @Test
    public void saveBusTest() throws ExistsTimeRowLogException {
        Bus bus = Bus.builder()
                .latitude(37.1234)
                .longitude(123.12356)
                .accuracy(90)
                .build();

//        busService.saveBus(bus);

    }
}