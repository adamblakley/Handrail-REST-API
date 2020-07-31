package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.*;
import com.orienteering.rest.demo.dto.EventDTO;
import com.orienteering.rest.demo.dto.UserDTO;
import com.orienteering.rest.demo.security.models.UserPrincipal;
import com.orienteering.rest.demo.service.EventService;
import com.orienteering.rest.demo.service.ImageUploadService;
import com.orienteering.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
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
    @PostMapping("/users/{id}/events")
    public ResponseEntity<StatusResponseEntity<EventDTO>> createEvent(@PathVariable Long id, @Valid @RequestPart("event")EventDTO eventDto, @RequestParam("file")MultipartFile file){

        ImageUploadResponse imageUploadResponse = uploadEventPhotograph(file);

        if (imageUploadResponse.getSuccess()) {
            Event event = convertToEntity(eventDto);
            EventPhotograph photograph = new EventPhotograph();
            photograph.setPhotoName(file.getOriginalFilename());
            photograph.setPhotoPath(imageUploadResponse.getFilepath());
            photograph.setEntity(event);
            event.setEventPhotograph(photograph);
            event.setEventStatus(1);
            event.setEventCreated(Calendar.getInstance().getTime());
            User user = userService.findUser(id);
            event.setEventOrganiser(user);
            eventService.saveEvent(event);
            EventDTO eventDTO = convertToDto(event);
            return new ResponseEntity( new StatusResponseEntity(true, "Event creation success",eventDTO), HttpStatus.OK);
        } else {
            return  new ResponseEntity( new StatusResponseEntity(false, "Event creation failed",false), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ImageUploadResponse uploadEventPhotograph(MultipartFile file){
        ImageUploadResponse imageUploadResponse = imageUploadService.uploadImage(file);
        return imageUploadResponse;
    }

    public StatusResponseEntity<Object> getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal){
            User user = userService.findUser(((UserPrincipal) principal).getId());
            return new StatusResponseEntity(true,"Got User",user);
        } else {
            return new StatusResponseEntity(false, "Failed to get User", false);
        }
    }


    private Event convertToEntity(EventDTO eventDto){
        return modelMapper.map(eventDto, Event.class);
    }

    public EventDTO convertToDto(Event event){
        return modelMapper.map(event,EventDTO.class);
    }

    private UserDTO convertToDto(User user){
        return modelMapper.map(user, UserDTO.class);
    }

}
