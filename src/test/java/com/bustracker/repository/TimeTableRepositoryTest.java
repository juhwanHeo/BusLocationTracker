package com.bustracker.repository;

import com.bustracker.entity.Station;
import com.bustracker.entity.Time;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeTable;
import com.bustracker.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /*
    *
    * 데이터베이스
    * 초기 세팅
    * */
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
                {"06:40", "06:45", "06:50", "06:55"},
                {"07:00", "07:05", "07:10", "07:15"},
                {"07:20", "07:25", "07:30", "07:35"},
                {"07:40", "07:45", "07:50", "07:55"},
                {"07:58", "08:03", "08:08", "08:13"},
                {"08:20", "08:25", "09:00", "09:05"},
                {"09:10", "09:15", "09:25", "09:30"},
                {"09:50", "09:55", "10:00", "10:05"},
                {"10:40", "10:45", "10:50", "11:00"},
                {"12:00", "12:05", "12:10", "12:15"},
                {"13:50", "13:55", "14:00", "14:05"},
                {"15:10", "15:15", "15:30", "15:35"},
                {"16:00", "16:05", "16:20", "16:25"},
                {"17:00", "17:05", "17:10", "17:15"},
                {"17:40", "17:45", "17:50", "17:55"},
                {"18:05", "18:10", "18:15", "18:20"},
                {"18:30", "18:40", "18:45", "18:50"},
                {"19:10", "19:15", "19:20", "19:25"},
                {"19:30", "19:35", "19:40", "19:45"},
                {"20:00", "20:05", "20:10", "20:15"},
                {"20:20", "20:25", "20:30", "20:35"},

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
            times.add(new Time(null, timeRow.getId(), 1, timeArray[index][0], stationList.get(0).getId(),  null));
            times.add(new Time(null, timeRow.getId(), 2, timeArray[index][1], stationList.get(1).getId(),  null));
            times.add(new Time(null, timeRow.getId(), 3, null, null,  null));
            times.add(new Time(null, timeRow.getId(), 4, null, null,  null));
            times.add(new Time(null, timeRow.getId(), 5, null, null,  null));
            times.add(new Time(null, timeRow.getId(), 6, null, null,  null));
            times.add(new Time(null, timeRow.getId(), 7, timeArray[index][2], stationList.get(2).getId(),  null));
            times.add(new Time(null, timeRow.getId(), 8, timeArray[index][3], stationList.get(0).getId(),  null));
            timeList.add(times);

            timeRow.setStartTime(times.get(0).getTime());
            timeRow.setStartTimeMillis(LocalTime.parse(times.get(0).getTime()).toNanoOfDay());

            timeRow.setEndTIme(times.get(times.size() - 1).getTime());
            timeRow.setEndTimeMillis(LocalTime.parse(times.get(times.size() - 1).getTime()).toNanoOfDay());
            index++;
        }
        // start, endTIme
        // update
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