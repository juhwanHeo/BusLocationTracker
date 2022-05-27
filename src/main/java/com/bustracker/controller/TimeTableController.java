package com.bustracker.controller;

import com.bustracker.exception.ResourceNotFound;
import com.bustracker.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/timetable")
public class TimeTableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping
    public ResponseEntity<?> find() throws ResourceNotFound {
        return ResponseEntity.ok().body(timetableService.find());
    }

}
