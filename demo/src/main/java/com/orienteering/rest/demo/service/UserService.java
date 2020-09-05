package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.User;
import com.orienteering.rest.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * Service queries repository for User objects
 */
@Service
public class UserService {

    /**
     * Default constructor
     */
    public UserService() {

    }
    // Repository to request database queries
    @Autowired
    UserRepository userRepository;

    /**
     * find all users
     * @return
     */
    @Transactional(readOnly = true)
    public List<User> findAllUsers(){
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    /**
     * save user
     * @param user
     * @return
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * find user by id
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public User findUser(Long id){
        return userRepository.findById(id).get();
    }

    /**
     * delete user
     * @param id
     */
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
