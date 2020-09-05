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

/**
 * Controller class for Photograph objects
 */
@RestController
public class PhotographController {
    // service class to request objects from the repository
    @Autowired
    ControlPhotographService controlPhotographService;
    // service class to request objects from the repository
    @Autowired
    EventPhotographService eventPhotographService;
    // service class to request objects from the repository
    @Autowired
    UserPhotographService userPhotographService;
    // model mapper, maps resources to DTOs
    @Autowired
    ModelMapper modelMapper;

    /**
     * Get all control photographs
     * @return
     */
    @GetMapping("/control/photographs")
    public List<PhotographDTO> retrieveAllControlPhotographs(){
        List<ControlPhotograph> photographs = controlPhotographService.findAllControlPhotographs();
        return photographs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Get control photograph by id
     * @param id
     * @return
     */
    @GetMapping("/control/photographs/{id}")
    public PhotographDTO retrieveControlPhotograph(@PathVariable Long id){
        ControlPhotograph controlPhotograph = controlPhotographService.findControlPhotograph(id);
        return convertToDto(controlPhotograph);
    }

    /**
     * Create control photograph
     * @param controlPhotograph
     * @return
     */
    @PostMapping("/control/photographs")
    public Long createControlPhotograph(@Valid @RequestBody ControlPhotograph controlPhotograph){
        controlPhotographService.saveControlPhotograph(controlPhotograph);
        return controlPhotograph.getPhotoId();
    }



    /**
     * Get all event photographs
     * @return
     */
    @GetMapping("/event/photographs")
    public List<PhotographDTO> retrieveAllEventPhotographs(){
        List<EventPhotograph> photographs = eventPhotographService.findAllEventPhotographs();
        return photographs.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    /**
     * Get event photograph by id
     * @param id
     * @return
     */
    @GetMapping("/event/photographs/{id}")
    public PhotographDTO retrieveEventPhotograph(@PathVariable Long id){
        EventPhotograph eventPhotograph = eventPhotographService.findEventPhotograph(id);
        return convertToDto(eventPhotograph);
    }
    /**
     * Create event photograph
     * @param eventPhotograph
     * @return
     */
    @PostMapping("/event/photographs")
    public Long createEventPhotograph(@Valid @RequestBody EventPhotograph eventPhotograph){
        eventPhotographService.saveEventPhotograph(eventPhotograph);
        return eventPhotograph.getPhotoId();
    }

    /**
     * Get all user photographs
     * @return
     */
    @GetMapping("/user/photographs")
    public List<PhotographDTO> retrieveAllUserPhotographs(){
        List<UserPhotograph> photographs = userPhotographService.findAllUserPhotographs();
        return photographs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Get user photograph by id
     * @param id
     * @return
     */
    @GetMapping("/user/photographs/{id}")
    public PhotographDTO retrieveUserPhotograph(@PathVariable Long id){
        UserPhotograph userPhotograph = userPhotographService.findUserPhotograph(id);
        return convertToDto(userPhotograph);
    }

    /**
     * Create user photograph
     * @param userPhotograph
     * @return
     */
    @PostMapping("/user/photographs")
    public Long createUserPhotograph(@Valid @RequestBody UserPhotograph userPhotograph){
        userPhotographService.saveUserPhotograph(userPhotograph);
        return userPhotograph.getPhotoId();
    }

    /**
     * Convert Photograph to PhotographDTO
     * @param photograph
     * @return
     */
    private PhotographDTO convertToDto(Photograph photograph){
        PhotographDTO photographDto = modelMapper.map(photograph,PhotographDTO.class);
        return photographDto;
    }

}
