package com.bustracker.controller;

import com.bustracker.entity.Station;
import com.bustracker.service.StationService;
import com.bustracker.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/{station-name}")
    public ResponseEntity<?> findByStationName(
            @PathVariable("station-name") String stationName
    ) {
        return ResponseEntity.ok().body(stationService.findByStationName(stationName));
    }

    @Secured({UserRole.ROLES.ADMIN, UserRole.ROLES.MANAGER})
    @PostMapping
    public ResponseEntity<?> saveStation(
            @RequestBody Station station
    ) {
        return ResponseEntity.ok().body(stationService.insert(station));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStation(
            @PathVariable("id") String id
    ) {
        stationService.delete(id);
        return ResponseEntity.ok().body("ok");
    }

}
