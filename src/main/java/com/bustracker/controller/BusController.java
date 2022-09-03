package com.bustracker.controller;

import com.bustracker.entity.Bus;
import com.bustracker.config.auth.UserDetailsImpl;
import com.bustracker.service.BusService;
import com.bustracker.enums.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/bus-logs")
public class BusController {

    @Autowired
    private BusService busService;



    @Secured({UserRole.ROLES.ADMIN, UserRole.ROLES.MANAGER, UserRole.ROLES.DRIVER})
    @GetMapping
    public ResponseEntity<?> findLastBus() {
        return ResponseEntity.ok().body(busService.findLastBus());
    }

    @Secured({UserRole.ROLES.ADMIN, UserRole.ROLES.MANAGER, UserRole.ROLES.DRIVER})
    @PostMapping
    public ResponseEntity<?> saveBusOld(
            @RequestBody Bus bus
            , Authentication auth
    ) {
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        log.info("user: {}, bus: {}", user, bus);
        return ResponseEntity.ok().body(busService.save(bus));
    }

//    @GetMapping("/running")
//    public ResponseEntity<?> findRunning() {
//        return ResponseEntity.ok().body(busService.findRunning());
//    }

//    @PatchMapping("/complete")
//    public ResponseEntity<?> updateComplete() {
//        return ResponseEntity.ok().body(busService.updateComplete());
//    }

//    @PostMapping
//    public ResponseEntity<?> saveBus(
//            @RequestBody Bus bus
//    ) throws ExistsTimeRowLogException {
//        log.info("bus: {}", bus);
//        return ResponseEntity.ok().body(busService.saveBus(bus));
//    }
}
