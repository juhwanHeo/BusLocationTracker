package com.bustracker.service;

import com.bustracker.entity.Station;
import com.bustracker.repository.StationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StationService {

	@Autowired
	private StationRepository stationRepository;

	public Station findById(String id) {
		return stationRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Not Found Station by id: " + id));
	}

	public List<Station> findAll() {
		return stationRepository.findAll();

	}

	public Station insert(Station station) {
		return stationRepository.save(station);

	}
}
