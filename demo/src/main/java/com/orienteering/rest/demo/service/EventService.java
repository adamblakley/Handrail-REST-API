package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.Event;
import com.orienteering.rest.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    public EventService() {
    }

    @Autowired
    EventRepository eventRepository;


    @Transactional(readOnly = true)
    public List<Event> findAllEvents(){
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(event -> events.add(event));
        return events;
    }

    /* Example. To Be ued in reference and deleted
    @Transactional(readOnly = true)
    public List<Event> findAllEventsByUser(Integer id){
        List<Event> events = new ArrayList<Event>();
        eventRepository.findAll().forEach(event -> {
            if (event.getEventOrganiser().getUserId().equals(id)){
                events.add(event);
            }
        });
        return events;
    }
    */

    public void saveEvent(Event event){
        eventRepository.save(event);
    }

    @Transactional(readOnly = true)
    public Event findEvent(Integer id){
        return eventRepository.findById(id).get();
    }

    public void deleteEvent(Integer id){
        eventRepository.deleteById(id);
    }
}
