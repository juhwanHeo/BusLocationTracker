package com.bustracker.repository.custom;


import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeRowLog;

public interface TimeRowCustomRepository {
    TimeRow findByCurrentTimeBetweenStartTimeAndEndTime(long currentTime);

}
