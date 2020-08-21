package com.orienteering.rest.demo.controller;


import com.orienteering.rest.demo.dto.*;

import com.orienteering.rest.demo.model.*;
import com.orienteering.rest.demo.service.EventService;
import com.orienteering.rest.demo.service.ParticipantControlPerformanceService;
import com.orienteering.rest.demo.service.RoutePointService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParticipantControlPerformanceController {


    @Autowired
    ParticipantControlPerformanceService participantControlPerformanceService;


    @Autowired
    EventService eventService;

    @Autowired
    RoutePointService routePointService;

    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/pcps")
    public List<ParticipantControlPerformanceDTO> retrieveAllParticipantControlPerformances(){

        List<ParticipantControlPerformance> participantControlPerformances = participantControlPerformanceService.findAllPcps();
        return participantControlPerformances.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/pcps/{id}")
    public ParticipantControlPerformanceDTO retrieveParticipantControlPerformance(@PathVariable Integer id){
        ParticipantControlPerformance participantControlPerformance = participantControlPerformanceService.findPcp(id);
        return convertToDto(participantControlPerformance);
    }


    @PostMapping("/events/{eventId}/users/{userId}/pcps")
    public ResponseEntity<StatusResponseEntity<?>> createParticipantControlPerformances(@PathVariable("eventId") Integer eventId, @PathVariable("userId") Long userId, @Valid @RequestBody PerformanceUploadRequest request){

        Participant participant = new Participant();
        Event event = eventService.findEvent(eventId);
        for (Participant eventParticipant : event.getParticipants()){
            if (eventParticipant.getParticipantUser().getUserId().equals(userId)){
                participant = eventParticipant;
                break;
            }
        }

        if (participant.getParticipantUser().getUserId()==null){
            return  new ResponseEntity( new StatusResponseEntity(false, "Participant not found",false), HttpStatus.NOT_FOUND);
        }

        for (ParticipantControlPerformanceDTO pcp : request.getPerformances()){
            ParticipantControlPerformance participantControlPerformanceToSave = convertToEntity(pcp);
            participantControlPerformanceToSave.setPcpParticipant(participant);
            participantControlPerformanceService.savePcp(participantControlPerformanceToSave);
        }

        for (RoutePointDTO routePoint : request.getRoutePoints()){
            RoutePoint routePointToSave = convertToEntity(routePoint);
            routePointToSave.setRoutePointParticipant(participant);
            routePointService.saveRoutePoint(routePointToSave);
        }

        ParticipantDTO participantDTO = convertToDto(participant);
        return  new ResponseEntity( new StatusResponseEntity(true, "Performances saved to Participant "+participantDTO.getParticipantId(),participantDTO), HttpStatus.CREATED);
    }

    public ParticipantControlPerformanceDTO convertToDto(ParticipantControlPerformance participantControlPerformance){
        ParticipantControlPerformanceDTO participantControlPerformanceDto = modelMapper.map(participantControlPerformance,ParticipantControlPerformanceDTO.class);
        return participantControlPerformanceDto;
    }

    public EventDTO convertToDto(Event event){
        EventDTO eventDto = modelMapper.map(event,EventDTO.class);
        return eventDto;
    }

    public ParticipantDTO convertToDto(Participant participant){
        ParticipantDTO participantDTO = modelMapper.map(participant,ParticipantDTO.class);
        return participantDTO;
    }

    private ParticipantControlPerformance convertToEntity(ParticipantControlPerformanceDTO pcpDto){
        ParticipantControlPerformance pcp = modelMapper.map(pcpDto, ParticipantControlPerformance.class);
        pcp.setPcpControl(convertToEntity(pcpDto.getPcpControl()));
        return pcp;
    }

    private RoutePoint convertToEntity(RoutePointDTO routePointDTO){
        RoutePoint routePoint = modelMapper.map(routePointDTO,RoutePoint.class);
        return routePoint;
    }

    private Control convertToEntity(ControlDTO controlDto){
        Control control = modelMapper.map(controlDto, Control.class);
        return control;
    }
}
