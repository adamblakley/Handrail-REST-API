package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.*;
import com.orienteering.rest.demo.dto.RoutePointDTO;
import com.orienteering.rest.demo.service.ParticipantService;
import com.orienteering.rest.demo.service.RoutePointService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoutePointResource {

    @Autowired
    RoutePointService routePointService;

    @Autowired
    ParticipantService participantService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/routepoints")
    public List<RoutePointDTO> retrieveAllRoutePoints(){
        List<RoutePoint> routePoints = routePointService.findAllRoutePoints();
        return routePoints.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/routepoints/{id}")
    public RoutePointDTO retrieveRoutePoint(@PathVariable Integer id){
        RoutePoint routePoint = routePointService.findRoutePoint(id);
        return convertToDto(routePoint);
    }

    @GetMapping("participants{id}/routepoints")
    public List<RoutePointDTO> retrieveRoutePointsByEvent(@PathVariable Integer id){

        Participant participant = participantService.findParticipant(id);
        List<RoutePoint> routePoints = participant.getRoutePoints();

        return routePoints.stream().map(this::convertToDto).collect(Collectors.toList());
    }



    @PostMapping("participants/{id}/routepoints")
    public Integer createRoutePoint(@PathVariable Integer id, @Valid @RequestBody RoutePointDTO routePointDTO){

        Participant participant = participantService.findParticipant(id);
        RoutePoint routePointToSave = convertToEntity(routePointDTO);
        routePointToSave.setRoutePointParticipant(participant);

        participant.addRoutePoint(routePointToSave);

        routePointService.saveRoutePoint(routePointToSave);

        participantService.saveParticipant(participant);

        return routePointDTO.getRoutePointId();
    }

    @PostMapping("participants/{id}/routepointsmany")
    public List<Integer> createRoutePoints(@PathVariable Integer id, @Valid @RequestBody List<RoutePointDTO> routePoints){

        Participant participant = participantService.findParticipant(id);
        List<Integer> ids = new ArrayList<Integer>();
        for (RoutePointDTO routePoint : routePoints){
            RoutePoint routePointToSave = convertToEntity(routePoint);
            routePointToSave.setRoutePointParticipant(participant);
            routePointService.saveRoutePoint(routePointToSave);
            ids.add(routePointToSave.getRoutePointId());
        }

        return ids;
    }

    public RoutePointDTO convertToDto(RoutePoint routePoint){
        RoutePointDTO routePointDto = modelMapper.map(routePoint,RoutePointDTO.class);
        return routePointDto;
    }

    private RoutePoint convertToEntity(RoutePointDTO routePointDto){
        RoutePoint routePoint = modelMapper.map(routePointDto, RoutePoint.class);
        return routePoint;
    }


}
