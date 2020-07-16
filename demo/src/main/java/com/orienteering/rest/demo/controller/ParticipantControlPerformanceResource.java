package com.orienteering.rest.demo.controller;


import com.orienteering.rest.demo.Control;
import com.orienteering.rest.demo.Event;
import com.orienteering.rest.demo.Participant;
import com.orienteering.rest.demo.ParticipantControlPerformance;

import com.orienteering.rest.demo.dto.ControlDTO;
import com.orienteering.rest.demo.dto.EventDTO;
import com.orienteering.rest.demo.dto.ParticipantControlPerformanceDTO;

import com.orienteering.rest.demo.service.ParticipantControlPerformanceService;
import com.orienteering.rest.demo.service.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParticipantControlPerformanceResource {


    @Autowired
    ParticipantControlPerformanceService participantControlPerformanceService;

    @Autowired
    ParticipantService participantService;

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

    @PostMapping("/participants/{id}/pcps")
    public Integer createParticipantControlPerformance(@PathVariable Integer id, @Valid @RequestBody ParticipantControlPerformanceDTO participantControlPerformanceDTO){

        Participant participant = participantService.findParticipant(id);

        ParticipantControlPerformance participantControlPerformanceToSave = convertToEntity(participantControlPerformanceDTO);


        participant.addPcp(participantControlPerformanceToSave);

        participantControlPerformanceService.savePcp(participantControlPerformanceToSave);

        participantService.saveParticipant(participant);

        return participantControlPerformanceDTO.getPcpId();
    }

    @PostMapping("/participants/{id}/pcpsmany")
    public List<Integer> createParticipantControlPerformances(@PathVariable Integer id, @Valid @RequestBody List<ParticipantControlPerformanceDTO> pcps){

        Participant participant = participantService.findParticipant(id);
        List<Integer> ids = new ArrayList<Integer>();
        for (ParticipantControlPerformanceDTO pcp : pcps){
            ParticipantControlPerformance participantControlPerformanceToSave = convertToEntity(pcp);
            participantControlPerformanceToSave.setPcpParticipant(participant);
            participantControlPerformanceService.savePcp(participantControlPerformanceToSave);
            ids.add(participantControlPerformanceToSave.getPcpId());
        }

        return ids;
    }

    public ParticipantControlPerformanceDTO convertToDto(ParticipantControlPerformance participantControlPerformance){
        ParticipantControlPerformanceDTO participantControlPerformanceDto = modelMapper.map(participantControlPerformance,ParticipantControlPerformanceDTO.class);
        return participantControlPerformanceDto;
    }

    public EventDTO convertToDto(Event event){
        EventDTO eventDto = modelMapper.map(event,EventDTO.class);
        return eventDto;
    }

    private ParticipantControlPerformance convertToEntity(ParticipantControlPerformanceDTO pcpDto){
        ParticipantControlPerformance pcp = modelMapper.map(pcpDto, ParticipantControlPerformance.class);
        pcp.setPcpControl(convertToEntity(pcpDto.getPcpControl()));
        return pcp;
    }



    private Control convertToEntity(ControlDTO controlDto){
        Control control = modelMapper.map(controlDto, Control.class);
        return control;
    }
}
