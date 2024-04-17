package com.example.picloud.services;

import com.example.picloud.entity.Event;
import com.example.picloud.repository.IEventRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventService implements IEventService{
IEventRepository IEventRepository;
    @Override
    public Event addEvent(Event event) {
        return IEventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvent() {
        return IEventRepository.findAll();
    }

    @Override
    public Event getEventById(long idEvent) {
        return IEventRepository.findById(idEvent).get();
    }

    @Override
    public void deleteEvent(long idEvent) {
        IEventRepository.deleteById(idEvent);
    }

    @Override
    public Event updateEvent(Event event) {
        return IEventRepository.save(event);
    }
}
