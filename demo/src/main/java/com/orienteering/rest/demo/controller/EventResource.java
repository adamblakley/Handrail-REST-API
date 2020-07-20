package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.Event;
import com.orienteering.rest.demo.ParticipantControlPerformance;
import com.orienteering.rest.demo.User;
import com.orienteering.rest.demo.dto.EventDTO;
import com.orienteering.rest.demo.service.EventService;
import com.orienteering.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventResource {

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/events")
    public List<EventDTO> retrieveAllEvents(){

        List<Event> events = eventService.findAllEvents();
        return events.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/events/{id}")
    public EventDTO retrieveEvents(@PathVariable Integer id){
        Event event = eventService.findEvent(id);
        return convertToDto(event);
    }

    @GetMapping("/users/{id}/events")
    public List<EventDTO> retrieveEventsByUser(@PathVariable Integer id){
        User user = userService.findUser(id);
        List<Event> events = user.getEvents();
        return events.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/events")
    public Integer createEvent(@Valid @RequestBody EventDTO eventDto){
        Event event = convertToEntity(eventDto);
        System.out.println(event.toString());
        eventService.saveEvent(event);
        return event.getEventID();
    }

    private Event convertToEntity(EventDTO eventDto){
        return modelMapper.map(eventDto, Event.class);
    }


    public EventDTO convertToDto(Event event){
        return modelMapper.map(event,EventDTO.class);
    }

}
