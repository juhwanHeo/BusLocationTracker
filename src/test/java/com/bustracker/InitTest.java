package com.bustracker;

import com.bustracker.entity.*;
import com.bustracker.exception.ExistsTimeRowLogException;
import com.bustracker.repository.*;
import com.bustracker.service.BusService;
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
class InitTest {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    /*
     *
     * 데이터베이스
     * 초기 세팅
     * */
    @Test
    public void setUp() {

        /*
        * facility
        * */
        Facility facility = Facility.builder()
                .facilityName("서희아파트")
                .facilityNameEng("seohui Apartment")
                .contactNumber("031-000-0000")
                .email("test@test.com")
                .build();

        facilityRepository.deleteAll();
        facilityRepository.save(facility);

        /*
        * station
        * */
        List<Station> list = new ArrayList<>();

        Station station1 = new Station(null, facility.getId(), "S001",  "서희아파트", 37.660935, 127.32249);
        Station station2 = new Station(null, facility.getId(),"S002", "마석역 1번출구", 37.652059, 127.311561);
        Station station3 = new Station(null, facility.getId(),"S003", "마석역 2번출구", 37.652995, 127.311207);
        Station station4 = new Station(null, facility.getId(),"S004", "심석 중.고", 37.656376, 127.305985);
        Station station5 = new Station(null, facility.getId(),"S005", "송라 초.중", 37.655009, 127.302257);
        Station station7 = new Station(null, facility.getId(),"S006", "마석 초.중", 37.648726, 127.305006);
        Station station8 = new Station(null, facility.getId(),"S007", "마석고", 37.644891, 127.300135);

        list.add(station1);
        list.add(station2);
        list.add(station3);
        list.add(station4);
        list.add(station5);
        list.add(station7);
        list.add(station8);

        stationRepository.deleteAll();
        stationRepository.saveAll(list);
        /*
         * timetable
         * */
        TimeTable timetable = TimeTable.builder()
                .facilityId(facility.getId())
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
                    .facilityId(facility.getId())
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
            times.add(new Time(null, facility.getId(),timeRow.getId(), 1, timeArray[index][0], stationList.get(0).getId(),  null));
            times.add(new Time(null, facility.getId(),timeRow.getId(), 2, timeArray[index][1], stationList.get(1).getId(),  null));
            times.add(new Time(null, facility.getId(),timeRow.getId(), 3, null, null,  null));
            times.add(new Time(null, facility.getId(),timeRow.getId(), 4, null, null,  null));
            times.add(new Time(null, facility.getId(),timeRow.getId(), 5, null, null,  null));
            times.add(new Time(null, facility.getId(),timeRow.getId(), 6, null, null,  null));
            times.add(new Time(null, facility.getId(),timeRow.getId(), 7, timeArray[index][2], stationList.get(2).getId(),  null));
            times.add(new Time(null, facility.getId(),timeRow.getId(), 8, timeArray[index][3], stationList.get(0).getId(),  null));
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
}