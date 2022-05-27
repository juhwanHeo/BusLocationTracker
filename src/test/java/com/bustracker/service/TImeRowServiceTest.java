package com.bustracker.service;

import com.bustracker.entity.TimeRow;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class TImeRowServiceTest {

    @Autowired
    private TimeRowService timeRowService;

    @Test
    public void findLastTimeRow() {
        List<TimeRow> timeRowList = timeRowService.findLastTimeRow();
        log.info("timeRow: {}", timeRowList);
    }
}