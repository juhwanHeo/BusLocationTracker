package com.bustracker.service;

import com.bustracker.dao.StationDAO;
import com.bustracker.dto.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StationService {

	private static final Logger logger = LogManager.getLogger(StationService.class);

	@Autowired
	private StationDAO stationDAO;

	public Station get(Map<String, Object> map) {
		Station station = null;

		try {
			station = stationDAO.get(map).get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return station;
	}

	public List<Station> getList(Map<String, Object> map) {
		return stationDAO.get(map);
	}

	public void add(Station.Request station) {
		stationDAO.add(station);
	}


}
