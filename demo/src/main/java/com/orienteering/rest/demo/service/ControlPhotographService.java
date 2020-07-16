package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.Control;

import com.orienteering.rest.demo.ControlPhotograph;
import com.orienteering.rest.demo.repository.ControlPhotographRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ControlPhotograph findControlPhotograph(Integer id) {
        return controlPhotographRepository.findById(id).get();
    }

    public void deleteControlPhotograph(Integer id) {
        controlPhotographRepository.deleteById(id);
    }
}