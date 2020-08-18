package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.dto.PhotographDTO;
import com.orienteering.rest.demo.model.ControlPhotograph;
import com.orienteering.rest.demo.model.EventPhotograph;
import com.orienteering.rest.demo.model.Photograph;
import com.orienteering.rest.demo.model.UserPhotograph;
import com.orienteering.rest.demo.service.ControlPhotographService;
import com.orienteering.rest.demo.service.EventPhotographService;
import com.orienteering.rest.demo.service.UserPhotographService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhotographController {

    @Autowired
    ControlPhotographService controlPhotographService;

    @Autowired
    EventPhotographService eventPhotographService;

    @Autowired
    UserPhotographService userPhotographService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/control/photographs")
    public List<PhotographDTO> retrieveAllControlPhotographs(){
        List<ControlPhotograph> photographs = controlPhotographService.findAllControlPhotographs();
        return photographs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/control/photographs/{id}")
    public PhotographDTO retrieveControlPhotograph(@PathVariable Long id){
        ControlPhotograph controlPhotograph = controlPhotographService.findControlPhotograph(id);
        return convertToDto(controlPhotograph);
    }

    @PostMapping("/control/photographs")
    public Long createControlPhotograph(@Valid @RequestBody ControlPhotograph controlPhotograph){
        controlPhotographService.saveControlPhotograph(controlPhotograph);
        return controlPhotograph.getPhotoId();
    }




    @GetMapping("/event/photographs")
    public List<PhotographDTO> retrieveAllEventPhotographs(){
        List<EventPhotograph> photographs = eventPhotographService.findAllEventPhotographs();
        return photographs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/event/photographs/{id}")
    public PhotographDTO retrieveEventPhotograph(@PathVariable Long id){
        EventPhotograph eventPhotograph = eventPhotographService.findEventPhotograph(id);
        return convertToDto(eventPhotograph);
    }

    @PostMapping("/event/photographs")
    public Long createEventPhotograph(@Valid @RequestBody EventPhotograph eventPhotograph){
        eventPhotographService.saveEventPhotograph(eventPhotograph);
        return eventPhotograph.getPhotoId();
    }




    @GetMapping("/user/photographs")
    public List<PhotographDTO> retrieveAllUserPhotographs(){
        List<UserPhotograph> photographs = userPhotographService.findAllUserPhotographs();
        return photographs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/user/photographs/{id}")
    public PhotographDTO retrieveUserPhotograph(@PathVariable Long id){
        UserPhotograph userPhotograph = userPhotographService.findUserPhotograph(id);
        return convertToDto(userPhotograph);
    }

    @PostMapping("/user/photographs")
    public Long createUserPhotograph(@Valid @RequestBody UserPhotograph userPhotograph){
        userPhotographService.saveUserPhotograph(userPhotograph);
        return userPhotograph.getPhotoId();
    }




    private PhotographDTO convertToDto(Photograph photograph){
        PhotographDTO photographDto = modelMapper.map(photograph,PhotographDTO.class);
        return photographDto;
    }

}
