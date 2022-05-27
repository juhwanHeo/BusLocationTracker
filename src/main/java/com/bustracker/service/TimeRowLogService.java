package com.bustracker.service;

import com.bustracker.entity.TimeRow;
import com.bustracker.entity.TimeRowLog;
import com.bustracker.repository.TimeRowLogRepository;
import com.bustracker.repository.TimeRowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;


@Slf4j
@Service
public class TimeRowLogService {

	@Autowired
	private TimeRowLogRepository timeRowLogRepository;


//	@Autowired
//	private TimeRowRepository timeRowRepository;

	public TimeRowLog findCurrentTimeRow() {
		TimeRowLog timeRowLog = timeRowLogRepository.findByCurrentTimeBetweenStartTimeAndEndTime(LocalTime.now().toNanoOfDay());
		return timeRowLog;
	}

	public TimeRowLog save(TimeRowLog timeRowLog) {
		return timeRowLogRepository.save(timeRowLog);
	}


}
