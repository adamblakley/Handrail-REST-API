package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.dto.EventDTO;
import com.orienteering.rest.demo.dto.ParticipantDTO;
import com.orienteering.rest.demo.model.Event;
import com.orienteering.rest.demo.model.Participant;
import com.orienteering.rest.demo.model.StatusResponseEntity;
import com.orienteering.rest.demo.model.User;
import com.orienteering.rest.demo.service.EventService;
import com.orienteering.rest.demo.service.ParticipantService;
import com.orienteering.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;
/**
 * Controller class for Participant objects
 */
@RestController
public class ParticipantController {
    // service class to request objects from the repository
    @Autowired
    ParticipantService participantService;
    // service class to request objects from the repository
    @Autowired
    EventService eventService;
    // service class to request objects from the repository
    @Autowired
    UserService userService;
    // model mapper, maps resources to DTOs
    @Autowired
    ModelMapper modelMapper;

    /**
     * Get participants by event
     * @param id
     * @return
     */
    @GetMapping("/events/{id}/participants")
    public ResponseEntity<StatusResponseEntity<?>> retrieveAllParticipants(@PathVariable Integer id){
        Event event = eventService.findEvent(id);
        ArrayList<Participant> participants = new ArrayList<Participant>(event.getParticipants());
        return new ResponseEntity( new StatusResponseEntity(true, "Participants found",participants.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
    }

    /**
     * Get participant results by event
     * @param id
     * @return
     */
    @GetMapping("/events/{id}/participants/results")
    public ResponseEntity<StatusResponseEntity<?>> retrieveAllParticipantsResults(@PathVariable Integer id){

        Event event = eventService.findEvent(id);

        // sort using comparator of total performance time
        Comparator<Participant> participantComparator = new Comparator<Participant>() {
            @Override
            public int compare(Participant a, Participant b) {
                Long aTime = a.totalPerformanceTime();
                Long bTime = b.totalPerformanceTime();
                return aTime.compareTo(bTime) ;
            }
        };

        ArrayList<Participant> participants = new ArrayList<Participant>(event.getParticipants());
        Collections.sort(participants,participantComparator);

        // remove if no time recorded or event unfinished by participant
        for (Iterator<Participant> iterator = participants.iterator(); iterator.hasNext();){
            Participant checkParticipant = iterator.next();
            if (checkParticipant.totalPerformanceTime()==0L){
                iterator.remove();
            }
        }

        return new ResponseEntity( new StatusResponseEntity(true, "Participants found",participants.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
    }

    /**
     * Return top 5 participants by event
     * @param id
     * @return
     */
    @GetMapping("/events/{id}/participants/top5")
    public ResponseEntity<StatusResponseEntity<?>> retrieveTop5Participants(@PathVariable Integer id){

        Event event = eventService.findEvent(id);

        // sort by total performance time
        Comparator<Participant> participantComparator = new Comparator<Participant>() {
            @Override
            public int compare(Participant a, Participant b) {
                Long aTime = a.totalPerformanceTime();
                Long bTime = b.totalPerformanceTime();
                return aTime.compareTo(bTime) ;
            }
        };

        ArrayList<Participant> participants = new ArrayList<Participant>(event.getParticipants());
        ArrayList<Participant> topParticipants = new ArrayList<>();
        Collections.sort(participants, participantComparator);
        for (int position =0; position<=4;position++){
            if (participants.size()>position) {
                // don't add if unfinished, remove and rest position
                if(participants.get(position).totalPerformanceTime()!=0){
                    topParticipants.add(participants.get(position));
                } else {
                    participants.remove(participants.get(position));
                    position--;
                }
            }
        }
        return new ResponseEntity( new StatusResponseEntity(true, "Top participants found",topParticipants.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
    }

    /**
     * Return participant by id
     * @param id
     * @return
     */
    @GetMapping("/participants/{id}")
    public ResponseEntity<StatusResponseEntity<?>>retrieveParticipant(@PathVariable Integer id){
        Participant participant = participantService.findParticipant(id);
        return new ResponseEntity( new StatusResponseEntity(true, "Participant found",convertToDto(participant)), HttpStatus.OK);

    }

    /**
     * Return participants by event and by user if event completed
     * @param eventId
     * @param userId
     * @return
     */
    @GetMapping("/events/{eventId}/users/{userId}/participants")
    public ResponseEntity<StatusResponseEntity<?>> retrievePerformance(@PathVariable Integer eventId, @PathVariable Long userId){
        Event event = eventService.findEvent(eventId);

        // sort participants by total compeleted time
        Comparator<Participant> participantComparator = new Comparator<Participant>() {
            @Override
            public int compare(Participant a, Participant b) {
                Long aTime = a.totalPerformanceTime();
                Long bTime = b.totalPerformanceTime();
                return aTime.compareTo(bTime) ;
            }
        };

        Collections.sort(event.getParticipants(),participantComparator);

        Participant participant = new Participant();

        // remove participant if uncompleted
        for (Iterator<Participant> iterator = event.getParticipants().iterator(); iterator.hasNext();) {
            Participant checkParticipant = iterator.next();
            if (checkParticipant.totalPerformanceTime() == 0L) {
                iterator.remove();
            }
            if (checkParticipant.getParticipantUser().getUserId().equals(userId)) {
                participant = checkParticipant;
            }
        }
        if (participant.getParticipantUser().getUserId()==null){
            return  new ResponseEntity( new StatusResponseEntity(false, "Participant not found",false), HttpStatus.NOT_FOUND);
        }
        // set participant position if event completed, return participant
        ParticipantDTO participantDTO = convertToDto(participant);
        if (event.getEventStatus()==3){
            participantDTO.setPosition(event.getParticipants().indexOf(participant)+1);
        }

        return  new ResponseEntity( new StatusResponseEntity(true, "Participant found",participantDTO), HttpStatus.OK);
    }

    /**
     * Add a participant to an event
     * @param id
     * @param userId
     * @return
     */
    @PostMapping("/events/{id}/participants")
    public ResponseEntity<StatusResponseEntity<?>> createParticipant(@PathVariable Integer id, @Valid @RequestBody Long userId){
        // find event, find user - create participant and set event and user
        Event event = eventService.findEvent(id);
        User user = userService.findUser(userId);
        Participant participant = new Participant();
        participant.setParticipantEvent(event);
        participant.setParticipantUser(user);
        event.addParticipant(participant);
        // save participant and event
        participantService.saveParticipant(participant);
        eventService.saveEvent(event);
        // convert to DTO and return event
        EventDTO eventDto = convertToDto(event);
        return new ResponseEntity(new StatusResponseEntity(false, "User not logged in", eventDto), HttpStatus.CREATED);
    }

    /**
     *  Remove participant from event
     * @param id
     * @param userId
     * @return
     */
    @PutMapping("/events/{id}/removeparticipant")
    public ResponseEntity<StatusResponseEntity<?>> removeParticipant(@PathVariable Integer id, @Valid @RequestBody Long userId){
        Event event = eventService.findEvent(id);
        for (Participant participant : event.getParticipants()){
            if (participant.getParticipantUser().getUserId()==userId){
                event.getParticipants().remove(participant);
                participant.setParticipantEvent(null);
                participantService.saveParticipant(participant);
                break;
            }
        }

        EventDTO eventDto = convertToDto(event);
        if (event.getEventID()==event.getEventID()){
            return new ResponseEntity(new StatusResponseEntity(false, "Participant Removed", eventDto), HttpStatus.OK);
        } else {
            return new ResponseEntity(new StatusResponseEntity(false, "Failure Removing Participant", eventDto), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Convert Participant to ParticipantDTO
     * @param participant
     * @return
     */
    public ParticipantDTO convertToDto(Participant participant){
        return modelMapper.map(participant,ParticipantDTO.class);
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
