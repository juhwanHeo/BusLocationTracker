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
                .latitude(37.644891)
                .longitude(127.300135)
                .build();
        Bus end = Bus.builder()
                .latitude(37.660935)
                .longitude(127.32249)
                .build();


        log.info("{}", DistanceUtils.distanceBus2Bus(start, end));
    }
}