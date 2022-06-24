package com.bustracker.repository.custom;

import com.bustracker.entity.Bus;

import java.util.List;

public interface BusCustomRepository {

    List<Bus> findRunning();
    Bus findBefore();

}
