package com.bustracker.controller;


import com.bustracker.dto.Bus;
import com.bustracker.service.BusService;
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
@RequestMapping(value = "/api/bus-logs")
public class BusController {

    private static final Logger logger = LogManager.getLogger(BusController.class);
	
	@Autowired
	private BusService busService;

	@GetMapping(value = "{seq_no}")
	public Bus.Response<?> get(@PathVariable int seq_no) {
		String msg = "ok";
		HttpStatus status;

		Map<String, Object> map = new HashMap<>();
		map.put("seq_no", seq_no);
		Bus bus = null;

		try {
			bus = busService.get(map);
			if (bus == null) throw new Exception("not found bus");

			status = HttpStatus.OK;
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}

		logger.info("[bus]: " + bus);
		return new Bus.Response<>(status.value(), msg, bus);
	}

	@GetMapping(value = "/current")
	public Bus.Response<?> getCurrent() {
		String msg = "ok";
		HttpStatus status;
		Bus bus = null;

		try {
			bus = busService.getCurrent();
			if (bus == null) throw new Exception("not found bus");
			status = HttpStatus.OK;
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}

		logger.info("[bus]: " + bus);
		return new Bus.Response<>(status.value(), msg, bus);
	}

	@GetMapping
	public Bus.Response<?> gets() {
		String msg = "ok";
		HttpStatus status;
		List<Bus> busList = null;

		try {
			busList = busService.getList(null);
			status = HttpStatus.OK;
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}

        logger.info(busList);
		return new Bus.Response<>(status.value(), msg, busList);
	}


	@PostMapping
	public Bus.Response<?> newBusLog(@RequestBody Bus.Request bus) {
		String msg = "ok";
		HttpStatus status;

		logger.info(bus);
		try {
			busService.add(bus);
			status = HttpStatus.OK;
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return new Bus.Response<>(status.value(), msg, bus);
	}

}
