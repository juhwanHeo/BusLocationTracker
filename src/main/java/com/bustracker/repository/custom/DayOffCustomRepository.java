package com.bustracker.repository.custom;

import com.bustracker.entity.DayOff;

import java.util.List;

public interface DayOffCustomRepository {
    List<DayOff> findAllByBetweenDate(String date);

}
