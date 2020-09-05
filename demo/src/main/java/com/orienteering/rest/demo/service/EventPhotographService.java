package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.EventPhotograph;
import com.orienteering.rest.demo.repository.EventPhotographRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Service queries repository for ControlPhotograph objects
 */
@Service
public class EventPhotographService {

    // Repository to request database queries
    @Autowired
    EventPhotographRepository eventPhotographRepository;

    /**
     * Default Constructor
     */
    public EventPhotographService() {
    }

    /**
     * Get all events
     * @return
     */
    public List<EventPhotograph> findAllEventPhotographs() {
        List<EventPhotograph> eventPhotographs = new ArrayList<EventPhotograph>();
        eventPhotographRepository.findAll().forEach(eventPhotograph -> eventPhotographs.add(eventPhotograph));
        return eventPhotographs;
    }

    /**
     * Save Event
     * @param controlPhotograph
     * @return
     */
    public Boolean saveEventPhotograph(EventPhotograph controlPhotograph) {
        if(eventPhotographRepository.save(controlPhotograph)!=null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get event by ID
     * @param id
     * @return
     */
    public EventPhotograph findEventPhotograph(Long id) {
        return eventPhotographRepository.findById(id).get();
    }

    /**
     * Delete Event
     * @param id
     */
    public void deleteEventPhotograph(Long id) {
        eventPhotographRepository.deleteById(id);
    }
}
