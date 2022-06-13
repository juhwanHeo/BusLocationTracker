package com.bustracker.service;

import com.bustracker.entity.Bus;
import com.bustracker.entity.Time;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeRowLog;
import com.bustracker.repository.BusRepository;
import com.bustracker.repository.TimeRowLogRepository;
import com.bustracker.repository.TimeRowRepository;
import com.bustracker.status.BusStatus;
import com.bustracker.status.TimeRowStatus;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {


    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRowLogRepository timeRowLogRepository;

    @Autowired
    private TimeRowService timeRowService;

    @Autowired
    private TimeRowLogService timeRowLogService;

    public List<Bus> findRunning() {
        return busRepository.findRunning();
    }

    public List<Bus> updateComplete() {
        List<Bus> busList = busRepository.findRunning();
        for (Bus bus : busList) bus.setStatus(BusStatus.COMPLETE);

        TimeRowLog timeRowLog = timeRowLogService.findById(busList.get(0).getTimeRowLogId());
        timeRowLog.setStatus(TimeRowStatus.COMPLETED);
        timeRowLogService.save(timeRowLog);

        return busRepository.saveAll(busList);
    }

    public Bus saveBus(Bus bus) {
        TimeRowLog timeRowLog = timeRowLogService.findCurrentTimeRowLog();
        if (timeRowLog != null) {
            bus.setTimeRowLogId(timeRowLog.getId());

            timeRowLog.setStatus(TimeRowStatus.IN_PROGRESS);
            timeRowLogService.save(timeRowLog);
        }
        else {
            TimeRowLog lastTimeRowLog = timeRowLogService.findTodayLastTimeRow()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("남은 운행 시간표가 없습니다."));

            bus.setTimeRowLogId(lastTimeRowLog.getId());

            lastTimeRowLog.setStatus(TimeRowStatus.IN_PROGRESS);
            timeRowLogService.save(lastTimeRowLog);
        }

        return busRepository.save(bus);
    }

    private int timeToMinute(LocalTime localTime) {
        return localTime.getHour() * 60 + localTime.getMinute();
    }
}
