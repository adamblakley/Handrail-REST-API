package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.model.Control;
import com.orienteering.rest.demo.dto.ControlDTO;
import com.orienteering.rest.demo.service.ControlService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for Control objects
 */
@RestController
public class ControlController {

    // service class to request objects from the repository
    @Autowired
    ControlService controlService;

    // model mapper, maps resources to DTOs
    @Autowired
    ModelMapper modelMapper;

    /**
     * Get a list of all controls
     * @return
     */
    @GetMapping("/controls")
    public List<ControlDTO> retrieveAllControl(){
        List<Control> controls = controlService.findAllControls();
        return controls.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Get a single control by id
     * @param id
     * @return
     */
    @GetMapping("/controls/{id}")
    public ControlDTO retrieveControl(@PathVariable Integer id){
        Control control = controlService.findControl(id);
        return convertToDto(control);
    }

    /**
     * Create a control
     * @param control
     * @return
     */
    @PostMapping("/controls")
    public Integer createControl(@Valid @RequestBody Control control){
        controlService.saveControl(control);
        return control.getControlId();
    }

    /**
     * Convert a Control to ControlDTO
     * @param control
     * @return
     */
    public ControlDTO convertToDto(Control control){
        ControlDTO controlDto = modelMapper.map(control,ControlDTO.class);
        return controlDto;
    }

}
