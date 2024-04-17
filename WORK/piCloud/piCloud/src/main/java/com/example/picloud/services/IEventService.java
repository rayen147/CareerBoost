package com.example.picloud.services;

import com.example.picloud.entity.Event;

import java.util.List;

public interface IEventService {
    Event addEvent(Event event);
    List<Event> getAllEvent();
    Event getEventById(long idEvent);
    void deleteEvent(long idEvent);
    Event updateEvent(Event event);
}
