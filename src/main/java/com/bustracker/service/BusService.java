package com.bustracker.service;

import com.bustracker.dao.BusDAO;
import com.bustracker.dto.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusService {

	private static final Logger logger = LogManager.getLogger(BusService.class);

	@Autowired
	private BusDAO busDAO;

	public Bus get(Map<String, Object> map){
		Bus bus = null;
		try {
			bus = busDAO.get(map).get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bus;
	}

	public List<Bus> getList(Map<String, Object> map) {
		return busDAO.get(map);
	}

	public void add(Bus.Request bus) {
		busDAO.add(bus);
	}

}
