package com.bustracker.service;


import com.bustracker.entity.Station;
import com.bustracker.entity.Time;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeTable;
import com.bustracker.exception.ResourceNotFound;
import com.bustracker.repository.StationRepository;
import com.bustracker.repository.TimeRepository;
import com.bustracker.repository.TimeRowRepository;
import com.bustracker.repository.TimeTableRepository;
import com.bustracker.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TimetableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private StationRepository stationRepository;

    public TimeTable find() throws ResourceNotFound {
        TimeTable timeTable = timeTableRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFound("Not Found TimeTable"));

        List<TimeRow> timeRowList = timeRowRepository.findAllByTimetableId(timeTable.getId());
        for (TimeRow timeRow : timeRowList) {
            List<Time> timeList = timeRepository.findAllByTimeRowId(timeRow.getId());
            timeRow.setTimeList(timeList);

            for (Time time : timeList) {
                stationRepository.findById(time.getStationId()).ifPresent(time::setStation);
            }
        }
        timeTable.setTimeRowList(timeRowList);
        return timeTable;
    }

}
