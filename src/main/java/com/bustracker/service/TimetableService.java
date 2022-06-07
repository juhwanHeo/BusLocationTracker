package com.bustracker.service;


import com.bustracker.entity.Time;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeTable;
import com.bustracker.exception.ResourceNotFoundException;
import com.bustracker.repository.StationRepository;
import com.bustracker.repository.TimeRepository;
import com.bustracker.repository.TimeRowRepository;
import com.bustracker.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public TimeTable find() throws ResourceNotFoundException {
        TimeTable timeTable = timeTableRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Not Found TimeTable"));

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
