package com.bustracker.utils;

import com.bustracker.entity.Bus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DistanceUtilsTest {

    @Test
    public void distance() {
        Bus start = Bus.builder()
                .lat(37.644891)
                .lon(127.300135)
                .build();
        Bus end = Bus.builder()
                .lat(37.660935)
                .lon(127.32249)
                .build();


        log.info("{}", DistanceUtils.distanceBus2Bus(start, end));
    }
}