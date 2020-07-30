package com.orienteering.rest.demo.service;


import com.orienteering.rest.demo.UserPhotograph;
import com.orienteering.rest.demo.repository.UserPhotographRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPhotographService {

    @Autowired
    UserPhotographRepository userPhotographRepository;

    public UserPhotographService() {
    }

    public List<UserPhotograph> findAllUserPhotographs() {
        List<UserPhotograph> userPhotographs = new ArrayList<UserPhotograph>();
        userPhotographRepository.findAll().forEach(userPhotograph -> userPhotographs.add(userPhotograph));
        return userPhotographs;
    }

    public void saveUserPhotograph(UserPhotograph controlPhotograph) {
        userPhotographRepository.save(controlPhotograph);
    }

    public UserPhotograph findUserPhotograph(Long id) {
        return userPhotographRepository.findById(id).get();
    }

    public void deleteUserPhotograph(Long id) {
        userPhotographRepository.deleteById(id);
    }
}
