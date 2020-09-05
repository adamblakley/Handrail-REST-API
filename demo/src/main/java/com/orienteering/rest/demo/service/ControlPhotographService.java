package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.ControlPhotograph;
import com.orienteering.rest.demo.repository.ControlPhotographRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service queries repository for ControlPhotograph objects
 */
@Service
public class ControlPhotographService {

    public ControlPhotographService() {
    }

    @Autowired
    ControlPhotographRepository controlPhotographRepository;

    public List<ControlPhotograph> findAllControlPhotographs() {
        List<ControlPhotograph> controlPhotographs = new ArrayList<ControlPhotograph>();
        controlPhotographRepository.findAll().forEach(controlPhotograph -> controlPhotographs.add(controlPhotograph));
        return controlPhotographs;
    }

    public void saveControlPhotograph(ControlPhotograph controlPhotograph) {
        controlPhotographRepository.save(controlPhotograph);
    }

    public ControlPhotograph findControlPhotograph(Long id) {
        return controlPhotographRepository.findById(id).get();
    }

    public void deleteControlPhotograph(Long id) {
        controlPhotographRepository.deleteById(id);
    }
}