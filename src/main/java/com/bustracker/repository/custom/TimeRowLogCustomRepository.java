package com.bustracker.repository.custom;


import com.bustracker.entity.TimeRowLog;

import java.util.List;

public interface TimeRowLogCustomRepository {
    TimeRowLog findRunning(long currentTime);

}
