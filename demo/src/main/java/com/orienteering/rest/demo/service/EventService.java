package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.Event;
import com.orienteering.rest.demo.model.Participant;
import com.orienteering.rest.demo.model.User;
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

    public List<Event> findEventsByParticipantHistory(User user) {

        List<Event> events= eventRepository.findByParticipants_ParticipantUser(user);
        List<Event> appropriateEvents = new ArrayList<Event>();

        for (Event event : events){
            for (Participant participant : event.getParticipants()){
                if (participant.getParticipantUser().equals(user)){
                    if (!participant.getParticipantControlPerformances().isEmpty()){
                        appropriateEvents.add(event);
                    }
                    break;
                }
            }
        }
        return appropriateEvents;
    }
    public List<Event> findActiveEvents(){return eventRepository.findByIsActiveTrue();}

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

    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }

    @Transactional(readOnly = true)
    public Event findEvent(Integer id){
        return eventRepository.findById(id).get();
    }

    public Boolean deleteEvent(Integer id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
