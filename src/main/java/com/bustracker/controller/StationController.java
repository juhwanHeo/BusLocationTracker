package com.bustracker.controller;

import com.bustracker.dto.Station;
import com.bustracker.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/stations")
public class StationController {

    private static final Logger logger = LogManager.getLogger(StationController.class);
	
	@Autowired
	private StationService stationService;

	@GetMapping(value = "{station_no}")
	public Station.Response<?> get(@PathVariable int station_no) {
		String msg = "ok";
		HttpStatus status;

		Map<String, Object> map = new HashMap<>();
		map.put("station_no", station_no);
		Station station = null;

		try {
			station = stationService.get(map);
			if (station == null) throw new Exception("not found station");

			status = HttpStatus.OK;
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}

		logger.info(station);
		return new Station.Response<>(status.value(), msg, station);
	}

	@GetMapping
	public Station.Response<?> gets() {
		String msg = "ok";
		HttpStatus status;
		List<Station> stationList = null;

		try {
			stationList = stationService.getList(null);
			status = HttpStatus.OK;
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}

		return new Station.Response<>(status.value(), msg, stationList);
	}

	@PostMapping
	public Station.Response<?> newBusLog(@RequestBody Station.Request station) {
		String msg = "ok";
		HttpStatus status;

		try {
			stationService.add(station);
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}

		logger.info("station: " + station);
		return new Station.Response<>(status.value(), msg, station);
	}
}
