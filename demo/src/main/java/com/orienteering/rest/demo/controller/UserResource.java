package com.orienteering.rest.demo.controller;


import com.orienteering.rest.demo.*;
import com.orienteering.rest.demo.dto.UserDTO;
import com.orienteering.rest.demo.service.ImageUploadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.orienteering.rest.demo.repository.UserRepository;
import com.orienteering.rest.demo.service.UserService;

import javax.validation.Valid;
import java.awt.*;
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

    @Autowired
    private ImageUploadService imageUploadService;

    @GetMapping("/users")
    public List<UserDTO> retrieveAllUsers(){
        List<User> users = userService.findAllUsers();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<StatusResponseEntity<?>> retrieveUser(@PathVariable Long id){
        User user = userService.findUser(id);
        return new ResponseEntity( new StatusResponseEntity(true, "User Find Successful",convertToDto(user)), HttpStatus.OK);
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

    @PutMapping("/users/{id}/update")
    public ResponseEntity<StatusResponseEntity<?>> updateUser(@PathVariable Long id, @Valid @RequestPart("user") UserDTO user, @RequestParam(value ="file", required=false) MultipartFile file){
        User foundUser = userService.findUser(id);

        if (user.getUserFirstName()!=null){
            foundUser.setUserFirstName(user.getUserFirstName());
        }
        if (user.getUserLastName()!=null){
            foundUser.setUserLastName(user.getUserLastName());
        }
        if (user.getUserEmail()!=null){
            foundUser.setUserEmail(user.getUserEmail());
        }
        if (user.getUserDob()!=null){
            foundUser.setUserDob(user.getUserDob());
        }
        if (user.getUserBio()!=null){
            foundUser.setUserBio(user.getUserBio());
        }

        if (file!=null){
            ImageUploadResponse imageUploadResponse = imageUploadService.uploadImage(file);

            if (imageUploadResponse.getSuccess()){
                UserPhotograph photograph = new UserPhotograph();
                photograph.setPhotoName(file.getOriginalFilename());
                photograph.setPhotoPath(imageUploadResponse.getFilepath());
                photograph.setEntity(foundUser);
                foundUser.setUserPhotograph(photograph);
            } else {
                userService.saveUser(foundUser);
                UserDTO userDTO = convertToDto(foundUser);
                return new ResponseEntity( new StatusResponseEntity(true, "User Update Partial Success. Image Update Failure",userDTO), HttpStatus.PARTIAL_CONTENT);
            }

        }
        userService.saveUser(foundUser);
        UserDTO userDTO = convertToDto(foundUser);
        return new ResponseEntity( new StatusResponseEntity(true, "User Update Successful",userDTO), HttpStatus.OK);
    }

    private UserDTO convertToDto(User user){
        UserDTO userDto = modelMapper.map(user,UserDTO.class);
        return userDto;
    }


}
