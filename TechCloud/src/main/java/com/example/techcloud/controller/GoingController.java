package com.example.techcloud.controller;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.entity.Going;
import com.example.techcloud.serviceimpl.GoingService;
import com.example.techcloud.serviceinterface.IEvenementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Goings")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class GoingController {
    private final GoingService goingService;
    private final IEvenementService eventService;
    @PostMapping("/mark-going")
    public String markAsGoing(
            @RequestBody Long eventId) {

        Evenement event = eventService.getEvenementById(eventId);


        try {
            goingService.markAsGoing(event);
            return "Marked as going";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/evenement/{eventId}")
    public List<Going> getGoingByEvent(@PathVariable Long eventId) {
        Evenement event = eventService.getEvenementById(eventId);
        return goingService.getGoingByEvent(event);
    }
}
