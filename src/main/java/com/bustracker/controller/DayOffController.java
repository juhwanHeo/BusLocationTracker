package com.bustracker.controller;

import com.bustracker.entity.DayOff;
import com.bustracker.service.DayOffService;
import com.bustracker.enums.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/day-off")
public class DayOffController {

    @Autowired
    private DayOffService dayOffService;

    @Secured({UserRole.ROLES.ADMIN, UserRole.ROLES.MANAGER})
    @PostMapping
    public ResponseEntity<DayOff> insertDayOff(@RequestBody DayOff dayOff) {
        log.info("DayOff {} ~ {} : {}", dayOff.getDayOffStatus(), dayOff.getEndDate(), dayOff.getDayOffStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(dayOffService.save(dayOff));
    }

    @Secured({UserRole.ROLES.ADMIN, UserRole.ROLES.MANAGER})
    @PatchMapping
    public ResponseEntity<DayOff> update(@RequestBody DayOff dayOff) {
        log.info("{} update: {}", dayOff.getId(), dayOff);
        return ResponseEntity.ok().body(dayOffService.save(dayOff));
    }

    @GetMapping("today")
    public ResponseEntity<List<DayOff>> findTodayDayOff() {
        return ResponseEntity.ok().body(dayOffService.findAllByToday());
    }
}
