package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.*;
import com.orienteering.rest.demo.dto.EventDTO;
import com.orienteering.rest.demo.service.EventService;
import com.orienteering.rest.demo.service.ImageUploadService;
import com.orienteering.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    ImageUploadService imageUploadService;

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
    public List<EventDTO> retrieveEventsByUser(@PathVariable Long id){
        User user = userService.findUser(id);
        List<Event> events = user.getEvents();
        return events.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    @PostMapping("/events")
    public ResponseEntity<StatusResponseEntity<Boolean>> createEvent(@Valid @RequestPart("event")EventDTO eventDto, @RequestParam("file")MultipartFile file){

        ImageUploadResponse imageUploadResponse = uploadEventPhotograph(file);

        if (imageUploadResponse.getSuccess()) {
            Event event = convertToEntity(eventDto);
            EventPhotograph photograph = new EventPhotograph();
            photograph.setPhotoName(file.getOriginalFilename());
            photograph.setPhotoPath(imageUploadResponse.getFilepath());
            photograph.setEntity(event);
            event.setEventPhotograph(photograph);
            eventService.saveEvent(event);
            
            return new ResponseEntity( new StatusResponseEntity(true, "Event creation success",true), HttpStatus.OK);
        }
        return  new ResponseEntity( new StatusResponseEntity(false, "Image upload failed",false), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ImageUploadResponse uploadEventPhotograph(MultipartFile file){
        ImageUploadResponse imageUploadResponse = imageUploadService.uploadImage(file);
        return imageUploadResponse;
    }


    private Event convertToEntity(EventDTO eventDto){
        return modelMapper.map(eventDto, Event.class);
    }

    public EventDTO convertToDto(Event event){
        return modelMapper.map(event,EventDTO.class);
    }

}
