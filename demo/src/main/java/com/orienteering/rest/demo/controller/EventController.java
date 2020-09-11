package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.dto.EventDTO;
import com.orienteering.rest.demo.dto.UserDTO;
import com.orienteering.rest.demo.model.*;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for Event objects
 */
@RestController
public class EventController {

    // service class to request objects from the repository
    @Autowired
    EventService eventService;
    // service class to request objects from the repository
    @Autowired
    UserService userService;
    // service class to request objects from the repository
    @Autowired
    ImageUploadService imageUploadService;
    // model mapper, maps resources to DTOs
    @Autowired
    ModelMapper modelMapper;

    /**
     * Get all events
     * @return
     */
    @GetMapping("/events")
    public ResponseEntity<StatusResponseEntity<?>> retrieveAllEvents(){
        List<Event> events = eventService.findActiveEvents();
        if (events.isEmpty()){
            return new ResponseEntity( new StatusResponseEntity(false, "No Events Available",false), HttpStatus.NOT_FOUND);
        } else {
            events.sort(Comparator.comparing(Event::getEventDate));
            return new ResponseEntity( new StatusResponseEntity(true, "Events Found",events.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
        }

    }

    /**
     * Get event by id
     * @param id
     * @return
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<StatusResponseEntity<?>> retrieveEvents(@PathVariable Integer id){
        Event event = eventService.findEvent(id);
        EventDTO eventDto = convertToDto(event);
        return new ResponseEntity( new StatusResponseEntity(true, "Event Update Successful",eventDto), HttpStatus.OK);
    }

    /**
     * Get events by user
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/events")
    public ResponseEntity<StatusResponseEntity<?>> retrieveEventsByUser(@PathVariable Long id){
        User user = userService.findUser(id);
        if (user.getUserType() == 1) {
            return new ResponseEntity(new StatusResponseEntity(false, "Admin Access", false), HttpStatus.FORBIDDEN);
        }
        List<Event> events = eventService.findEventByOrganiser(user);
        return new ResponseEntity( new StatusResponseEntity(true, "Events Found",events.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
    }

    /**
     * Get events by user history
     * @param id
     * @return
     */
    @GetMapping("users/{id}/events/history")
    public ResponseEntity<StatusResponseEntity<List<EventDTO>>> retrieveEventsByUserHistory(@PathVariable Long id){
        User user = userService.findUser(id);
        List<Event> events = eventService.findEventsByParticipantHistory(user);
        return new ResponseEntity( new StatusResponseEntity(true, "Events Found",events.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
    }

    /**
     * Delete event
     * @param id
     * @return
     */
    @PutMapping("/events/{id}/delete")
    public ResponseEntity<StatusResponseEntity<Boolean>> deleteEvent (@PathVariable Integer id){
        Event event = eventService.findEvent(id);
        // if event exists, set inactive. if already inactive, return conflict
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


    /**
     * Update event
     * @param id
     * @param eventDto
     * @return
     */
    @PutMapping("events/{id}/update")
    public ResponseEntity<StatusResponseEntity<EventDTO>> updateEventInformation(@PathVariable Integer id, @Valid @RequestBody EventDTO eventDto){

        Event serverEvent = eventService.findEvent(id);

        if (eventDto.getEventName()!=null){
            serverEvent.setEventName(eventDto.getEventName());
        }
        if (eventDto.getEventNote()!=null){
            serverEvent.setEventNote(eventDto.getEventNote());
        }
        if (eventDto.getEventDate()!=null){
            serverEvent.setEventDate(eventDto.getEventDate());
        }

        if (serverEvent.getEventID()==eventService.saveEvent(serverEvent).getEventID()){
            EventDTO returnedEventDto = convertToDto(serverEvent);
            return new ResponseEntity( new StatusResponseEntity(true, "Event Update Successful",returnedEventDto), HttpStatus.OK);
        } else {
            return new ResponseEntity( new StatusResponseEntity(false, "Event Update Failure",false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update event with image update
     * @param id
     * @param eventDto
     * @param file
     * @return
     */
    @PostMapping("events/{id}/update")
    public ResponseEntity<StatusResponseEntity<EventDTO>> updateEventInformationWphoto(@PathVariable Integer id, @Valid @RequestPart("event")EventDTO eventDto, @RequestPart(value ="file", required=false) MultipartFile file){

        Event serverEvent = eventService.findEvent(id);

        if (eventDto.getEventName()!=null){
            serverEvent.setEventName(eventDto.getEventName());
        }
        if (eventDto.getEventNote()!=null){
            serverEvent.setEventNote(eventDto.getEventNote());
        }
        if (eventDto.getEventDate()!=null){
            serverEvent.setEventDate(eventDto.getEventDate());
        }

        // if image!=null, save image, return filepath and create new photo with filepath
        if (file!=null){
            ImageUploadResponse imageUploadResponse = imageUploadService.uploadFileRequest(file);

            if (imageUploadResponse.getSuccess()){
                EventPhotograph photograph = new EventPhotograph();
                photograph.setPhotoName(file.getOriginalFilename());
                photograph.setPhotoPath(imageUploadResponse.getFilepath());
                photograph.setActive(true);
                photograph.setEntity(serverEvent);

                if (serverEvent.getEventPhotographs()==null){
                    serverEvent.setEventPhotographs(new ArrayList<EventPhotograph>());
                }
                // set all event photographs to false
                for (Photograph photographThru : serverEvent.getEventPhotographs()){
                    photographThru.setActive(false);
                }
                // add new event photograph
                serverEvent.getEventPhotographs().add(photograph);
            } else {
                eventService.saveEvent(serverEvent);
                EventDTO returnedEventDto = convertToDto(serverEvent);
                return new ResponseEntity( new StatusResponseEntity(true, "Event Update Partial Success. Image Update Failure",returnedEventDto), HttpStatus.PARTIAL_CONTENT);
            }

        }
        // save and return event
        if (serverEvent.getEventID()==eventService.saveEvent(serverEvent).getEventID()){
            EventDTO returnedEventDto = convertToDto(serverEvent);
            return new ResponseEntity( new StatusResponseEntity(true, "Event Update Successful",returnedEventDto), HttpStatus.OK);
        } else {
            return new ResponseEntity( new StatusResponseEntity(false, "Event Update Failure",false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * update event status
     * @param id
     * @return
     */
    @PutMapping("events/{id}/updatestatus")
    public ResponseEntity<StatusResponseEntity<EventDTO>> updateEventStatus(@PathVariable Integer id){
        Event event = eventService.findEvent(id);
        // switch for event status, change to 2 or 3 based on original status
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
            return new ResponseEntity( new StatusResponseEntity(false, "Event Update Failure",false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create a new event
     * @param id
     * @param eventDto
     * @param file
     * @return
     */
    @Transactional
    @PostMapping("/users/{id}/events")
    @ResponseBody
    public ResponseEntity<StatusResponseEntity<EventDTO>> createEvent(@PathVariable Long id, @Valid @RequestPart("event")EventDTO eventDto, @RequestParam("file")MultipartFile file){
        // get an upload response from the imageuload service
        ImageUploadResponse imageUploadResponse = uploadEventPhotograph(file);

        // if image upload is success, then create event from DTO, set image file, set to active, set creation time and save
        if (imageUploadResponse.getSuccess()) {
            Event event = convertToEntity(eventDto);
            EventPhotograph photograph = new EventPhotograph();
            photograph.setPhotoName(file.getOriginalFilename());
            photograph.setPhotoPath(imageUploadResponse.getFilepath());
            photograph.setActive(true);
            photograph.setEntity(event);
            event.setEventPhotographs(new ArrayList<EventPhotograph>());
            event.getEventPhotographs().add(photograph);
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

    /**
     * Get image upload response from imageuploadservice.
     * @param file
     * @return
     */
    public ImageUploadResponse uploadEventPhotograph(MultipartFile file){
        ImageUploadResponse imageUploadResponse = imageUploadService.uploadFileRequest(file);
        return imageUploadResponse;
    }

    /**
     * Get user from security context holder principle
     * @return
     */
    public StatusResponseEntity<Object> getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal){
            User user = userService.findUser(((UserPrincipal) principal).getId());
            return new StatusResponseEntity(true,"Got User",user);
        } else {
            return new StatusResponseEntity(false, "Failed to get User", false);
        }
    }


    /**
     * Convert EventDTO to Event
     * @param eventDto
     * @return
     */
    private Event convertToEntity(EventDTO eventDto){
        return modelMapper.map(eventDto, Event.class);
    }

    /**
     * Convert Event to EventDTO
     * @param event
     * @return
     */
    public EventDTO convertToDto(Event event){
        return modelMapper.map(event,EventDTO.class);
    }

}
