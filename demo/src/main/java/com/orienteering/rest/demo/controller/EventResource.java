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
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Calendar;
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
    public ResponseEntity<StatusResponseEntity<?>> retrieveAllEvents(){
        List<Event> events = eventService.findActiveEvents();
        if (events.isEmpty()){
            return new ResponseEntity( new StatusResponseEntity(false, "No Events Available",false), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity( new StatusResponseEntity(true, "Events Found",events.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
        }

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

    @GetMapping("users/{id}/events/history")
    public ResponseEntity<StatusResponseEntity<List<EventDTO>>> retrieveEventsByUserHistory(@PathVariable Long id){
        User user = userService.findUser(id);
        List<Event> events = eventService.findEventsByParticipantHistory(user);
        return new ResponseEntity( new StatusResponseEntity(true, "Events Found",events.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
    }

    @PutMapping("/events/{id}/delete")
    public ResponseEntity<StatusResponseEntity<Boolean>> deleteEvent (@PathVariable Integer id){
        Event event = eventService.findEvent(id);
        if (event!=null){
            if (!event.isActive()){
                return new ResponseEntity( new StatusResponseEntity(false, "Event Already Removed",false), HttpStatus.CONFLICT);
            }
            event.setActive(false);
            eventService.saveEvent(event);
            return new ResponseEntity( new StatusResponseEntity(true, "Event Successfully Deleted",true), HttpStatus.OK);
        } else {
            return new ResponseEntity( new StatusResponseEntity(false, "Event Removal Unsuccessful",false), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("events/{id}/updatestatus")
    public ResponseEntity<StatusResponseEntity<EventDTO>> updateEventStatus(@PathVariable Integer id){
        Event event = eventService.findEvent(id);
        switch (event.getEventStatus()){
            case 1: event.setEventStatus(2);
                break;
            case 2: event.setEventStatus(3);
                break;
            default:
                break;
        }
        if (event.getEventID()==eventService.saveEvent(event).getEventID()){
            EventDTO eventDto = convertToDto(event);
            return new ResponseEntity( new StatusResponseEntity(true, "Event Update Successful",eventDto), HttpStatus.OK);
        } else {
            return new ResponseEntity( new StatusResponseEntity(false, "Event Update Successful",false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PostMapping("/users/{id}/events")
    public ResponseEntity<StatusResponseEntity<EventDTO>> createEvent(@PathVariable Long id, @Valid @RequestPart("event")EventDTO eventDto, @RequestParam("file")MultipartFile file){
        System.out.println(file.getResource().getFilename());
        ImageUploadResponse imageUploadResponse = uploadEventPhotograph(file);

        if (imageUploadResponse.getSuccess()) {
            Event event = convertToEntity(eventDto);
            EventPhotograph photograph = new EventPhotograph();
            photograph.setPhotoName(file.getOriginalFilename());
            photograph.setPhotoPath(imageUploadResponse.getFilepath());
            photograph.setEntity(event);
            event.setEventPhotograph(photograph);
            event.setEventStatus(1);
            event.setActive(true);
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
