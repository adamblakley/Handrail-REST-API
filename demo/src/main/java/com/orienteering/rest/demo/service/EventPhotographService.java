package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.EventPhotograph;
import com.orienteering.rest.demo.repository.EventPhotographRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventPhotographService {

    @Autowired
    EventPhotographRepository eventPhotographRepository;

    public EventPhotographService() {
    }

    public List<EventPhotograph> findAllEventPhotographs() {
        List<EventPhotograph> eventPhotographs = new ArrayList<EventPhotograph>();
        eventPhotographRepository.findAll().forEach(eventPhotograph -> eventPhotographs.add(eventPhotograph));
        return eventPhotographs;
    }

    public Boolean saveEventPhotograph(EventPhotograph controlPhotograph) {
        if(eventPhotographRepository.save(controlPhotograph)!=null){
            return true;
        } else {
            return false;
        }
    }

    public EventPhotograph findEventPhotograph(Long id) {
        return eventPhotographRepository.findById(id).get();
    }

    public void deleteEventPhotograph(Long id) {
        eventPhotographRepository.deleteById(id);
    }
}
