package com.orienteering.rest.demo.controller;


import com.orienteering.rest.demo.ParticipantControlPerformance;
import com.orienteering.rest.demo.User;
import com.orienteering.rest.demo.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.orienteering.rest.demo.repository.UserRepository;
import com.orienteering.rest.demo.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserResource {

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    public List<UserDTO> retrieveAllUsers(){
        List<User> users = userService.findAllUsers();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserDTO retrieveUser(@PathVariable Long id){
        User user = userService.findUser(id);
        return convertToDto(user);
    }

    @PostMapping("/users")
    public Long createUser(@Valid @RequestBody User user){
        userService.saveUser(user);
        return user.getUserId();
    }

    @PostMapping("/usersmany")
    public List<Long> createParticipantControlPerformances(@Valid @RequestBody List<User> users){
        List<Long> ids = new ArrayList<Long>();
        for (User user : users){
            userService.saveUser(user);
            ids.add(user.getUserId());
        }
        return ids;
    }

    private UserDTO convertToDto(User user){
        UserDTO userDto = modelMapper.map(user,UserDTO.class);
        return userDto;
    }


}
