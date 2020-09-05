package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.UserPhotograph;
import com.orienteering.rest.demo.repository.UserPhotographRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Service queries repository for UserPhotograph objects
 */
@Service
public class UserPhotographService {
    // Repository to request database queries
    @Autowired
    UserPhotographRepository userPhotographRepository;

    /**
     * default constructor
     */
    public UserPhotographService() {
    }

    /**
     * find all user photographs
     * @return
     */
    public List<UserPhotograph> findAllUserPhotographs() {
        List<UserPhotograph> userPhotographs = new ArrayList<UserPhotograph>();
        userPhotographRepository.findAll().forEach(userPhotograph -> userPhotographs.add(userPhotograph));
        return userPhotographs;
    }

    /**
     * save user photograph
     * @param controlPhotograph
     */
    public void saveUserPhotograph(UserPhotograph controlPhotograph) {
        userPhotographRepository.save(controlPhotograph);
    }

    /**
     * find user photograph by id
     * @param id
     * @return
     */
    public UserPhotograph findUserPhotograph(Long id) {
        return userPhotographRepository.findById(id).get();
    }

    /**
     * delete user photograph
     * @param id
     */
    public void deleteUserPhotograph(Long id) {
        userPhotographRepository.deleteById(id);
    }
}
