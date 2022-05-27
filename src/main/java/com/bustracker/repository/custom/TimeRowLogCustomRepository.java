package com.bustracker.repository.custom;


import com.bustracker.entity.TimeRowLog;

public interface TimeRowLogCustomRepository {
    TimeRowLog findByCurrentTimeBetweenStartTimeAndEndTime(long currentTime);

}
