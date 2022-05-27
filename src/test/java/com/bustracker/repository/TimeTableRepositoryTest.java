package com.bustracker.repository;

import com.bustracker.entity.Station;
import com.bustracker.entity.Time;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeTable;
import com.bustracker.status.TimeRowStatus;
import com.bustracker.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TimeTableRepositoryTest {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void setUp() {
        /*
        * timetable
        * */
        String now = DateUtils.dateToString(new Date());
        TimeTable timetable = TimeTable.builder()
                .inputDate(now)
                .updateDate(now)
                .build();

        String[][] timeArray = {
                {"07:20", "07:25", "07:30", "07:35"},
                {"07:40", "07:45", "07:50", "07:55"},
                {"08:20", "08:25", "08:30", "08:35"},
                {"08:40", "08:45", "08:50", "08:55"},
                {"15:40", "15:45", "15:50", "15:55"},
                {"20:40", "20:45", "20:50", "20:55"},
        };

        timeTableRepository.deleteAll();
        timeTableRepository.save(timetable);

        /*
        * timeROw
        * */
        List<TimeRow> timeRowList = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            TimeRow timeRow = TimeRow.builder()
                    .timetableId(timetable.getId())
                    .order(i + 1)
                    .build();

            timeRowList.add(timeRow);
        }

        timeRowRepository.deleteAll();
        timeRowRepository.saveAll(timeRowList);

        /*
        * time
        * */
        List<Station> stationList = stationRepository.findAll();
        int index = 0;

        List<List<Time>> timeList = new ArrayList<>();
        for (TimeRow timeRow : timeRowList) {
            List<Time> times = new ArrayList<>();
            times.add(new Time(null, timeRow.getId(), 1, stationList.get(0).getId(), timeArray[index][0], null));
            times.add(new Time(null, timeRow.getId(), 2, stationList.get(1).getId(), timeArray[index][1], null));
            times.add(new Time(null, timeRow.getId(), 3, stationList.get(2).getId(), timeArray[index][2], null));
            times.add(new Time(null, timeRow.getId(), 4, stationList.get(0).getId(), timeArray[index][3], null));
            timeList.add(times);

            timeRow.setStartTime(times.get(0).getTime());
            timeRow.setStartTimeMillis(LocalTime.parse(times.get(0).getTime()).toNanoOfDay());

            timeRow.setEndTIme(times.get(times.size() - 1).getTime());
            timeRow.setEndTimeMillis(LocalTime.parse(times.get(times.size() - 1).getTime()).toNanoOfDay());
            index++;
        }
        // start, endTIme
        // update
        timeRowList.get(4).setStatus(TimeRowStatus.IN_PROGRESS);
        timeRowRepository.saveAll(timeRowList);

        timeRepository.deleteAll();
        for (List<Time> times : timeList) timeRepository.saveAll(times);

    }

    @Test
    public void updateTest() {
        String now = DateUtils.dateToString(new Date());
        TimeTable timeTable = timeTableRepository.findAll().get(0);

        timeTableRepository.save(timeTable);
    }

    @Test
    public void findAll() {
        List<TimeTable> list = timeTableRepository.findAll();

        log.info("list: {}", list);
        log.info("LocalTime.now(): {}", LocalTime.now());
    }

}