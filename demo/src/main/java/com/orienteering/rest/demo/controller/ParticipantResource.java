package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.*;
import com.orienteering.rest.demo.dto.ControlDTO;
import com.orienteering.rest.demo.dto.EventDTO;
import com.orienteering.rest.demo.dto.ParticipantControlPerformanceDTO;
import com.orienteering.rest.demo.dto.ParticipantDTO;
import com.orienteering.rest.demo.service.EventService;
import com.orienteering.rest.demo.service.ParticipantService;
import com.orienteering.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParticipantResource {

    @Autowired
    ParticipantService participantService;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/events/{id}/participants")
    public List<ParticipantDTO> retrieveAllParticipants(@PathVariable Integer id){

        Event event = eventService.findEvent(id);

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
        return participants.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/events/{id}/participants/top5")
    public List<ParticipantDTO> retrieveTop5Participants(@PathVariable Integer id){

        Event event = eventService.findEvent(id);

        Comparator<Participant> participantComparator = new Comparator<Participant>() {
            @Override
            public int compare(Participant a, Participant b) {
                Long aTime = a.totalPerformanceTime();
                Long bTime = b.totalPerformanceTime();
                return bTime.compareTo(aTime) ;
            }
        };

        ArrayList<Participant> participants = new ArrayList<Participant>(event.getParticipants());
        Collections.sort(participants, participantComparator);
        ArrayList<Participant> topParticipants = (ArrayList<Participant>)participants.subList(0,5);

        return topParticipants.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/participants/{id}")
    public ParticipantDTO retrieveParticipant(@PathVariable Integer id){
        Participant participant = participantService.findParticipant(id);
        return convertToDto(participant);
    }

    @PostMapping("/events/{id}/participants")
    public ResponseEntity<StatusResponseEntity<?>> createParticipant(@PathVariable Integer id, @Valid @RequestBody Long userId){

        Event event = eventService.findEvent(id);
        User user = userService.findUser(userId);
        Participant participant = new Participant();
        participant.setParticipantEvent(event);
        participant.setParticipantUser(user);
        event.addParticipant(participant);
        participantService.saveParticipant(participant);
        eventService.saveEvent(event);
        ParticipantDTO participantDto = convertToDto(participant);
        EventDTO eventDto = convertToDto(event);
        return new ResponseEntity(new StatusResponseEntity(false, "User not logged in", eventDto), HttpStatus.CREATED);
    }

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

       // Event eventSaved = eventService.saveEvent(event);

        if (event.getEventID()==event.getEventID()){
            EventDTO eventDto = convertToDto(event);
            return new ResponseEntity(new StatusResponseEntity(false, "Participant Removed", eventDto), HttpStatus.OK);
        } else {
            EventDTO eventDto = convertToDto(event);
            return new ResponseEntity(new StatusResponseEntity(false, "Failure Removing Participant", eventDto), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ParticipantDTO convertToDto(Participant participant){
        return modelMapper.map(participant,ParticipantDTO.class);
    }

    public EventDTO convertToDto(Event event){
        return modelMapper.map(event,EventDTO.class);
    }

    private Participant convertToEntity(ParticipantDTO participantDto){
        return modelMapper.map(participantDto, Participant.class);
    }

}
