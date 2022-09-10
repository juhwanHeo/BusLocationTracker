package com.bustracker.service;

import com.bustracker.entity.Time;
import com.bustracker.entity.TimeLog;
import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeRowLog;
import com.bustracker.exception.ExistsTimeRowLogException;
import com.bustracker.repository.TimeLogRepository;
import com.bustracker.repository.TimeRepository;
import com.bustracker.repository.TimeRowLogRepository;
import com.bustracker.repository.TimeRowRepository;
import com.bustracker.enums.TimeRowStatus;
import com.bustracker.enums.TimeStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class TimeRowLogService {

    @Autowired
    private TimeRowLogRepository timeRowLogRepository;


    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TimeLogRepository timeLogRepository;

//	@Autowired
//	private TimeRowRepository timeRowRepository;

    public TimeRowLog findCurrentTimeRowLog() {
        long time = LocalTime.now().toNanoOfDay();
        TimeRowLog timeRowLog = null;
        try {
            timeRowLog = timeRowLogRepository.findRunning(time);
        } catch (Exception e) {
            log.warn("{}", e.getMessage());
        }

        return timeRowLog;
    }

    public TimeRowLog save(TimeRowLog timeRowLog) {
        return timeRowLogRepository.save(timeRowLog);
    }

    public TimeRowLog findById(String id) {
        return timeRowLogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Running Bus by timeRowLog id: " + id));
    }

    public List<TimeRowLog> findByRunningDate(String date) {
        return timeRowLogRepository.findByRunningDate(LocalDate.parse(date).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public List<TimeRowLog> findByToday() {
        return timeRowLogRepository.findByRunningDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public boolean isExistTodayTimeRowLog() {
        return timeRowLogRepository.existsByRunningDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public List<TimeRowLog> findTodayLastTimeRow() {
        return timeRowLogRepository.findByTodayLastTimeRow().stream()
                .filter(timeRowLog -> {
                    LocalTime endTime = LocalTime.parse(timeRowLog.getEndTIme());
                    LocalTime nowTime = LocalTime.now();

                    boolean isPass = nowTime.isBefore(endTime) || timeRowLog.getStatus() == TimeRowStatus.IN_PROGRESS;
                    if (!isPass) {
                        log.info("timeRowLog: {}",timeRowLog);
                        timeRowLog.setStatus(TimeRowStatus.NOT_PROGRESS);
                        timeRowLogRepository.save(timeRowLog);
                    }

                    return isPass;
                })
                .collect(Collectors.toList());
    }

    /*
    * 운행 날짜 TimeRowLog 초기회
    * */
    public void initToday(boolean isForce) throws ExistsTimeRowLogException {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<TimeRowLog> timeRowLogList = timeRowLogRepository.findByRunningDate(today);
        log.info("timeRowLog: {}", timeRowLogList);

        if (isForce || (timeRowLogList == null || timeRowLogList.isEmpty())) {
            // 버스 시간표에서 현재 운행해야하는 TimeRow 를 가져옴
            List<TimeRow> timeRowList = timeRowRepository.findAll();
            for (TimeRow timeRow : timeRowList) {
                TimeRowLog timeRowLog = timeRow.convertLog();
                LocalTime startTime = LocalTime.parse(timeRowLog.getStartTime());
                LocalTime endTime = LocalTime.parse(timeRowLog.getEndTIme());
                LocalTime nowTime = LocalTime.now();

                log.info("now: {}:{}", nowTime.getHour(), nowTime.getMinute());
                log.info("{} ~ {}", timeRowLog.getStartTime(), timeRowLog.getEndTIme());
                log.info("startTime.isBefore: {}", startTime.isBefore(nowTime));
                log.info("startTime.isAfter: {}", startTime.isAfter(nowTime));

                log.info("endTime.isBefore: {}", endTime.isBefore(nowTime));
                log.info("endTime.isAfter: {}", endTime.isAfter(nowTime));
                log.info("=========================\n");

                timeRowLog.setRunningDate(today);
                if (nowTime.isBefore(endTime) && nowTime.isAfter(startTime)) timeRowLog.setStatus(TimeRowStatus.IN_PROGRESS);
                else if (nowTime.isBefore(startTime)) timeRowLog.setStatus(TimeRowStatus.STAND_BY);
                else if (nowTime.isAfter(endTime)) timeRowLog.setStatus(TimeRowStatus.NOT_PROGRESS);

                timeRowLogRepository.save(timeRowLog);
                List<Time> timeList = timeRepository.findAllByTimeRowId(timeRow.getId());
                for (Time time : timeList) {
                    TimeLog timeLog = time.convertLog();
                    LocalTime timeLogEnd = LocalTime.parse(timeLog.getTime());

                    timeLog.setTimeRowLogId(timeRowLog.getId());
                    if (nowTime.isBefore(timeLogEnd)) timeLog.setStatus(TimeStatus.STAND_BY);
                    else timeLog.setStatus(TimeStatus.COMPLETED);

                    timeLogRepository.save(timeLog);
                }
            }
        }
        else throw new ExistsTimeRowLogException("운행중입니다..");
    }

    public List<TimeRowLog> saveAll(List<TimeRowLog> timeRowLogList) {
        return timeRowLogRepository.saveAll(timeRowLogList);
    }
//    public void updateStatus(List<String> timeRowIdList, TimeRowStatus status) {
//        List<TimeRowLog> timeRowLogList = timeRowLogRepository.findAllById(timeRowIdList).;
//
//    }
}
