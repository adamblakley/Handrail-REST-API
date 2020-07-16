package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.Control;
import com.orienteering.rest.demo.ControlPhotograph;
import com.orienteering.rest.demo.service.ControlPhotographService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ControlPhotographResource {

    @Autowired
    ControlPhotographService controlPhotographService;

    @GetMapping("/controlphotographs")
    public List<ControlPhotograph> retrieveAllControl(){
        return controlPhotographService.findAllControlPhotographs();
    }

    @GetMapping("/controlphotographs/{id}")
    public ControlPhotograph retrieveControl(@PathVariable Integer id){
        ControlPhotograph controlPhotograph = controlPhotographService.findControlPhotograph(id);
        return controlPhotograph;
    }

    @PostMapping("/controlphotographs")
    public Integer createControlPhotograph(@Valid @RequestBody ControlPhotograph controlPhotograph){
        controlPhotographService.saveControlPhotograph(controlPhotograph);
        return controlPhotograph.getControlPhotoId();
    }

}
