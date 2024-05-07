package com.example.techcloud.controller;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.entity.Likes;
import com.example.techcloud.serviceimpl.LikesService;
import com.example.techcloud.serviceinterface.IEvenementService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/likes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikesController {
    private final LikesService likeService;
    private final IEvenementService eventService;
    @PostMapping("/add-like/{event-id}")
    public String addLike(@PathVariable("event-id") Long event_id ,@RequestBody Long eventId) {


        Evenement event = eventService.getEvenementById(event_id);


        try {
            likeService.addLike(event);
            return "Like added";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/evenementt/{eventId}")
    public List<Likes> getLikesByEvent(@PathVariable Long eventId) {
        Evenement event = eventService.getEvenementById(eventId);
        return likeService.getLikesByEvent(event);
    }
}
