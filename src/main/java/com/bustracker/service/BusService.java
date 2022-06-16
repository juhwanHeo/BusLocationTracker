package com.bustracker.service;

import com.bustracker.entity.*;
import com.bustracker.exception.ExistsTimeRowLogException;
import com.bustracker.repository.*;
import com.bustracker.status.TimeRowStatus;
import com.bustracker.status.TimeStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class BusService {


    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TimeRowRepository timeRowRepository;

    @Autowired
    private TimeRowLogRepository timeRowLogRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TimeLogRepository timeLogRepository;

    @Autowired
    private TimeRowService timeRowService;

    @Autowired
    private TimeRowLogService timeRowLogService;

    public List<Bus> findRunning() {
        return busRepository.findRunning();
    }

    public List<Bus> updateComplete() {
        List<Bus> busList = busRepository.findRunning();
//        for (Bus bus : busList) bus.setStatus(BusStatus.COMPLETE);

        TimeRowLog timeRowLog = timeRowLogService.findById(busList.get(0).getTimeRowLogId());
        timeRowLog.setStatus(TimeRowStatus.COMPLETED);
        timeRowLogService.save(timeRowLog);

        return busRepository.saveAll(busList);
    }

    public Bus saveBus(Bus bus) throws ExistsTimeRowLogException {
        if (!timeRowLogService.isExistTodayTimeRowLog()) timeRowLogService.initToday(false);

        TimeRowLog timeRowLog = timeRowLogService.findCurrentTimeRowLog();
        if (timeRowLog != null) {
            bus.setTimeRowLogId(timeRowLog.getId());

            if (this.isCompleted(timeRowLog, bus)) timeRowLog.setStatus(TimeRowStatus.COMPLETED);
            else timeRowLog.setStatus(TimeRowStatus.IN_PROGRESS);

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

    // TODO: 2022/06/16
    // private
    public boolean isCompleted(TimeRowLog timeRowLog, Bus bus) {
        List<TimeLog> timeLogList = timeLogRepository.findByTimeRowLogId(timeRowLog.getId());

        for (TimeLog timeLog : timeLogList) {
            if (timeLog.getStatus() == TimeStatus.COMPLETED) continue;

            // 시작
            if (timeLog.getOrder() == 1) {
                timeLog.setStatus(TimeStatus.COMPLETED);
                timeLogRepository.save(timeLog);
                break;

            }
            // 마지막 정거장
            else if (timeLog.getOrder() == timeLogList.size() - 1){
                /*
                * 거리 판단
                * 마지막 정거장이랑 50M (테스트 진행중 거리는 변경 가능)
                * 정도의 거리면 완료 처리
                * */

                return true;
            }
            else {
                /*
                 * 거리 판단
                 * 처음과 마지막을 제외한 정거장이랑 50M (테스트 진행중 거리는 변경 가능)
                 * 정도의 거리면 완료 처리
                 *
                 * if 거리 50M 이하
                 * timeLog.setStatus(TimeStatus.COMPLETED);
                 * timeLogRepository.save(timeLog);
                 * else break;
                 * */


            }
        }

        return false;
    }

    private int timeToMinute(LocalTime localTime) {
        return localTime.getHour() * 60 + localTime.getMinute();
    }
}
