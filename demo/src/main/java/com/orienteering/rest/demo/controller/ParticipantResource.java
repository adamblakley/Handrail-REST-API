package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.Control;
import com.orienteering.rest.demo.Event;
import com.orienteering.rest.demo.Participant;
import com.orienteering.rest.demo.ParticipantControlPerformance;
import com.orienteering.rest.demo.dto.ControlDTO;
import com.orienteering.rest.demo.dto.EventDTO;
import com.orienteering.rest.demo.dto.ParticipantControlPerformanceDTO;
import com.orienteering.rest.demo.dto.ParticipantDTO;
import com.orienteering.rest.demo.service.EventService;
import com.orienteering.rest.demo.service.ParticipantService;
import com.orienteering.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Integer createParticipant(@PathVariable Integer id, @Valid @RequestBody ParticipantDTO participantDTO){

        Event event = eventService.findEvent(id);

        Participant participantToSave = convertToEntity(participantDTO);
        participantToSave.setParticipantEvent(event);
        event.addParticipant(participantToSave);

        participantService.saveParticipant(participantToSave);
        eventService.saveEvent(event);

        return participantDTO.getParticipantId();
    }


    public ParticipantDTO convertToDto(Participant participant){
        return modelMapper.map(participant,ParticipantDTO.class);
    }

    private Participant convertToEntity(ParticipantDTO participantDto){
        return modelMapper.map(participantDto, Participant.class);
    }

}
