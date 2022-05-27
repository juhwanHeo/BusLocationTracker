package com.bustracker.service;

import com.bustracker.entity.TimeRow;
import com.bustracker.repository.TimeRowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;


@Slf4j
@Service
public class TimeRowService {

	@Autowired
	private TimeRowRepository timeRowRepository;

	/*
	* 남은 운행 가져오기
	* */
	public List<TimeRow> findLastTimeRow() {
		return timeRowRepository.findByStartTimeMillisIsGreaterThanEqual(LocalTime.now().toNanoOfDay());
	}

	public TimeRow findCurrentTimeRow() {
		return timeRowRepository.findByCurrentTimeBetweenStartTimeAndEndTime(LocalTime.now().toNanoOfDay());
	}
}
