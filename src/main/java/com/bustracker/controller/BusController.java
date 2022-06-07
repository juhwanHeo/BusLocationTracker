package com.bustracker.controller;


import com.bustracker.entity.Bus;
import com.bustracker.service.BusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/bus-logs")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/running")
    public ResponseEntity<?> findRunning() {
        return ResponseEntity.ok().body(busService.findRunning());
    }

    @PatchMapping("/complete")
    public ResponseEntity<?> updateComplete() {
        return ResponseEntity.ok().body(busService.updateComplete());
    }

    @PostMapping
    public ResponseEntity<?> saveBus(
            @RequestBody Bus bus
    ) {
        log.info("bus: {}", bus);
        return ResponseEntity.ok().body(busService.saveBus(bus));
    }
}
