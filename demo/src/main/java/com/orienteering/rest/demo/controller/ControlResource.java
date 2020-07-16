package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.Control;
import com.orienteering.rest.demo.Course;
import com.orienteering.rest.demo.ParticipantControlPerformance;
import com.orienteering.rest.demo.dto.ControlDTO;
import com.orienteering.rest.demo.dto.ParticipantControlPerformanceDTO;
import com.orienteering.rest.demo.service.ControlService;
import com.orienteering.rest.demo.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ControlResource {

    @Autowired
    ControlService controlService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/controls")
    public List<ControlDTO> retrieveAllControl(){
        List<Control> controls = controlService.findAllControls();
        return controls.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/controls/{id}")
    public ControlDTO retrieveControl(@PathVariable Integer id){
        Control control = controlService.findControl(id);
        return convertToDto(control);
    }

    @PostMapping("/controls")
    public Integer createControl(@Valid @RequestBody Control control){
        controlService.saveControl(control);
        return control.getControlId();
    }

    public ControlDTO convertToDto(Control control){
        ControlDTO controlDto = modelMapper.map(control,ControlDTO.class);
        return controlDto;
    }

}
