package com.bustracker.service;

import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeRowLog;
import com.bustracker.exception.ExistsTimeRowLogException;
import com.bustracker.repository.TimeRowLogRepository;
import com.bustracker.repository.TimeRowRepository;
import com.bustracker.status.TimeRowStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@Service
public class TimeRowLogService {

    @Autowired
    private TimeRowLogRepository timeRowLogRepository;


    @Autowired
    private TimeRowRepository timeRowRepository;


//	@Autowired
//	private TimeRowRepository timeRowRepository;

    public TimeRowLog findCurrentTimeRowLog() {
        long time = LocalTime.now().toNanoOfDay();
        log.info("current: {}", time);
        TimeRowLog timeRowLog = timeRowLogRepository.findRunning(time);
        return timeRowLog;
    }

    public TimeRowLog save(TimeRowLog timeRowLog) {
        return timeRowLogRepository.save(timeRowLog);
    }

    public TimeRowLog findById(String id) {
        return timeRowLogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Running Bus by timeRowLog id: " + id));
    }

    /*
    * 운행 날짜 TimeRowLog 초기회
    * */
    public void init(boolean isForce) throws ExistsTimeRowLogException {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<TimeRowLog> timeRowLogList = timeRowLogRepository.findByToday(today);

        log.info("timeRowLog: {}", timeRowLogList);

        if (isForce || (timeRowLogList == null || timeRowLogList.isEmpty())) {
            // 버스 시간표에서 현재 운행해야하는 TimeRow 를 가져옴
            List<TimeRow> timeRowList = timeRowRepository.findAll();
            for (TimeRow timeRow : timeRowList) {
                TimeRowLog newTimeRowLog = timeRow.convertLog();
                LocalTime startTime = LocalTime.parse(newTimeRowLog.getStartTime());
                LocalTime endTime = LocalTime.parse(newTimeRowLog.getEndTIme());
                LocalTime nowTime = LocalTime.now();

                newTimeRowLog.setToday(today);

                log.info("now: {}:{}", nowTime.getHour(), nowTime.getMinute());
                log.info("{} ~ {}", newTimeRowLog.getStartTime(), newTimeRowLog.getEndTIme());
                log.info("startTime.isBefore: {}", startTime.isBefore(nowTime));
                log.info("startTime.isAfter: {}", startTime.isAfter(nowTime));

                log.info("endTime.isBefore: {}", endTime.isBefore(nowTime));
                log.info("endTime.isAfter: {}", endTime.isAfter(nowTime));
                log.info("=========================\n");

                if (nowTime.isBefore(endTime) && nowTime.isAfter(startTime)) newTimeRowLog.setStatus(TimeRowStatus.IN_PROGRESS);
                else if (nowTime.isBefore(startTime)) newTimeRowLog.setStatus(TimeRowStatus.STAND_BY);
                else if (nowTime.isAfter(endTime)) newTimeRowLog.setStatus(TimeRowStatus.NOT_PROGRESS);

                timeRowLogRepository.save(newTimeRowLog);
            }
            log.info("nowTime: {}", LocalTime.now());
        }
        else throw new ExistsTimeRowLogException("운행중입니다..");

    }

}
