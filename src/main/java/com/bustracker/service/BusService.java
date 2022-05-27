package com.bustracker.service;

import com.bustracker.entity.Bus;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeRowLog;
import com.bustracker.repository.BusRepository;
import com.bustracker.status.BusStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {


    @Autowired
    private BusRepository busRepository;

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

        return busRepository.saveAll(busList);
    }

    public Bus saveBus(Bus bus) {
        TimeRowLog timeRowLog = timeRowLogService.findCurrentTimeRow();
        if (timeRowLog == null) {
            TimeRow timeRow = timeRowService.findCurrentTimeRow();
            timeRowLogService.save(timeRow.convertLog());
        }
        bus.setTimeRowLogId(timeRowLog.getId());
        return busRepository.save(bus);
    }
}
