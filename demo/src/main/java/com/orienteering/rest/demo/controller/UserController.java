package com.orienteering.rest.demo.controller;


import com.orienteering.rest.demo.dto.UserDTO;
import com.orienteering.rest.demo.model.*;
import com.orienteering.rest.demo.security.models.UserPrincipal;
import com.orienteering.rest.demo.security.models.PasswordUpdateRequest;
import com.orienteering.rest.demo.service.ImageUploadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.orienteering.rest.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @PutMapping("/users/{id}/update/password")
    public ResponseEntity<StatusResponseEntity<?>> updateUserPassword(@PathVariable Long id, @Valid @RequestBody PasswordUpdateRequest request){

        Object prinicipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (prinicipal instanceof UserPrincipal){
            if (((UserPrincipal) prinicipal).getId().equals(id)){
                User user = userService.findUser(id);
                if (passwordEncoder.matches(request.getCurrentPassword(),user.getUserPassword())){
                    user.setUserPassword(passwordEncoder.encode(request.getNewPassword()));
                    userService.saveUser(user);
                    return new ResponseEntity( new StatusResponseEntity(true, "Password Update Successful",convertToDto(user)), HttpStatus.OK);
                } else {
                    return new ResponseEntity( new StatusResponseEntity(true, "Password Incorrect",false), HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity( new StatusResponseEntity(false, "Unauthorized Request",false), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity( new StatusResponseEntity(false, "Service Unavailable",false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}/update")
    public ResponseEntity<StatusResponseEntity<?>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO user){
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
        userService.saveUser(foundUser);
        UserDTO userDTO = convertToDto(foundUser);
        return new ResponseEntity( new StatusResponseEntity(true, "User Update Successful",userDTO), HttpStatus.OK);
    }

    @PostMapping("/users/{id}/update")
    public ResponseEntity<StatusResponseEntity<?>> updateUserWPhoto(@PathVariable Long id, @Valid @RequestPart("user") UserDTO user, @RequestPart(value ="file", required=false) MultipartFile file){
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
                photograph.setActive(true);
                photograph.setEntity(foundUser);

                if (foundUser.getUserPhotographs()==null){
                    foundUser.setUserPhotographs(new ArrayList<UserPhotograph>());
                }

                for (Photograph photographThru : foundUser.getUserPhotographs()){
                    photographThru.setActive(false);
                }

                foundUser.getUserPhotographs().add(photograph);
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
