package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.User;
import com.orienteering.rest.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserService() {

    }

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAllUsers(){
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }


    @Transactional(readOnly = true)
    public User findUser(Long id){
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
