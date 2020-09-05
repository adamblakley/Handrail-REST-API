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

/**
 * Controller class for PCP objects
 */
@RestController
public class ParticipantControlPerformanceController {

    // service class to request objects from the repository
    @Autowired
    ParticipantControlPerformanceService participantControlPerformanceService;
    // service class to request objects from the repository
    @Autowired
    EventService eventService;
    // service class to request objects from the repository
    @Autowired
    RoutePointService routePointService;
    // model mapper, maps resources to DTOs
    @Autowired
    ModelMapper modelMapper;

    /**
     * Get all PCPS
     * @return
     */
    @GetMapping("/pcps")
    public List<ParticipantControlPerformanceDTO> retrieveAllParticipantControlPerformances(){

        List<ParticipantControlPerformance> participantControlPerformances = participantControlPerformanceService.findAllPcps();
        return participantControlPerformances.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Get PCP by id
     * @param id
     * @return
     */
    @GetMapping("/pcps/{id}")
    public ParticipantControlPerformanceDTO retrieveParticipantControlPerformance(@PathVariable Integer id){
        ParticipantControlPerformance participantControlPerformance = participantControlPerformanceService.findPcp(id);
        return convertToDto(participantControlPerformance);
    }


    /**
     * Create PCPs by event and by user
     * @param eventId
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/events/{eventId}/users/{userId}/pcps")
    public ResponseEntity<StatusResponseEntity<?>> createParticipantControlPerformances(@PathVariable("eventId") Integer eventId, @PathVariable("userId") Long userId, @Valid @RequestBody PerformanceUploadRequest request){

        // get participant by participant by user
        Participant participant = new Participant();
        Event event = eventService.findEvent(eventId);
        for (Participant eventParticipant : event.getParticipants()){
            if (eventParticipant.getParticipantUser().getUserId().equals(userId)){
                participant = eventParticipant;
                break;
            }
        }
        // not found
        if (participant.getParticipantUser().getUserId()==null){
            return  new ResponseEntity( new StatusResponseEntity(false, "Participant not found",false), HttpStatus.NOT_FOUND);
        }
        // convert pcpDTO to pcp and set participant, save
        for (ParticipantControlPerformanceDTO pcp : request.getPerformances()){
            ParticipantControlPerformance participantControlPerformanceToSave = convertToEntity(pcp);
            participantControlPerformanceToSave.setPcpParticipant(participant);
            participantControlPerformanceService.savePcp(participantControlPerformanceToSave);
        }
        // convert routepointDTO to routePoint, set participant and save
        for (RoutePointDTO routePoint : request.getRoutePoints()){
            RoutePoint routePointToSave = convertToEntity(routePoint);
            routePointToSave.setRoutePointParticipant(participant);
            routePointService.saveRoutePoint(routePointToSave);
        }
        // return DTO
        ParticipantDTO participantDTO = convertToDto(participant);
        return  new ResponseEntity( new StatusResponseEntity(true, "Performances saved to Participant "+participantDTO.getParticipantId(),participantDTO), HttpStatus.CREATED);
    }

    /**
     * Convert PCP to PCPDTO
     * @param participantControlPerformance
     * @return
     */
    public ParticipantControlPerformanceDTO convertToDto(ParticipantControlPerformance participantControlPerformance){
        ParticipantControlPerformanceDTO participantControlPerformanceDto = modelMapper.map(participantControlPerformance,ParticipantControlPerformanceDTO.class);
        return participantControlPerformanceDto;
    }

    /**
     * Convert Participant to ParticipantDTO
     * @param participant
     * @return
     */
    public ParticipantDTO convertToDto(Participant participant){
        ParticipantDTO participantDTO = modelMapper.map(participant,ParticipantDTO.class);
        return participantDTO;
    }

    /**
     * Convert PCPDTO to PCP
     * @param pcpDto
     * @return
     */
    private ParticipantControlPerformance convertToEntity(ParticipantControlPerformanceDTO pcpDto){
        ParticipantControlPerformance pcp = modelMapper.map(pcpDto, ParticipantControlPerformance.class);
        pcp.setPcpControl(convertToEntity(pcpDto.getPcpControl()));
        return pcp;
    }

    /**
     * Convert RoutePointDTO to RoutePoint
     * @param routePointDTO
     * @return
     */
    private RoutePoint convertToEntity(RoutePointDTO routePointDTO){
        RoutePoint routePoint = modelMapper.map(routePointDTO,RoutePoint.class);
        return routePoint;
    }

    /**
     * Convert ControlDTO to Control
     * @param controlDto
     * @return
     */
    private Control convertToEntity(ControlDTO controlDto){
        Control control = modelMapper.map(controlDto, Control.class);
        return control;
    }
}
