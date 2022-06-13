package com.bustracker.controller;

import com.bustracker.exception.ExistsTimeRowLogException;
import com.bustracker.service.TimeRowLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/time-row-logs")
public class TimeRowLogController {

    @Autowired
    private TimeRowLogService timeRowLogService;


    @GetMapping("/current")
    public ResponseEntity<?> findCurrentTimeRowLog() {
        return ResponseEntity.ok().body(timeRowLogService.findCurrentTimeRowLog());
    }

    @GetMapping("/today")
    public ResponseEntity<?> findTodayTimeRowLog() {
        return ResponseEntity.ok().body(timeRowLogService.findTodayTimeRowLog());
    }

    @GetMapping("/today/last")
    public ResponseEntity<?> findTodayLastTimeRowLog() {
        return ResponseEntity.ok().body(timeRowLogService.findTodayLastTimeRow());
    }

    @PostMapping("/today/init/{isForce}")
    public ResponseEntity<?> initToday(@PathVariable boolean isForce) throws ExistsTimeRowLogException {
        timeRowLogService.initToday(isForce);
        return ResponseEntity.ok("ok");
    }
}
