package com.bustracker.service;

import com.bustracker.entity.DayOff;
import com.bustracker.repository.DayOffRepository;
import com.bustracker.status.DayOffStatus;
import com.bustracker.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DayOffService {

    @Autowired
    private DayOffRepository dayOffRepository;

    public DayOff save(DayOff dayOff) {
        return dayOffRepository.save(dayOff);
    }

    /*
    * Today 휴일 조회
    * 하루에 등록된 휴일 종류를 가져온다.
    * */
    public List<DayOff> findAllByToday() {
        String today = DateUtils.getToday("yyyyMMdd");
        if (DateUtils.Holiday.isHoliday(today)) {
            DayOff dayOff = DayOff.builder()
                    .startDate(today)
                    .endDate(today)
                    .message("법정 공휴일")
                    .dayOffStatus(DayOffStatus.PUBLIC_HOLIDAY)
                    .build();
            return Collections.singletonList(dayOff);
        }

        return dayOffRepository.findAllByBetweenDate(today);
    }
}
