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

/**
 * Controller class for User objects
 */
@RestController
public class UserController {
    // service class to request objects from the repository
    @Autowired
    UserService userService;
    // authenticate password updates
    @Autowired
    AuthenticationManager authenticationManager;
    // password encoder for updating passwords
    @Autowired
    PasswordEncoder passwordEncoder;
    // model mapper, maps resources to DTOs
    @Autowired
    private ModelMapper modelMapper;
    // service class to request objects from the repository
    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * Get user by id
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<StatusResponseEntity<?>> retrieveUser(@PathVariable Long id) {
        User user = userService.findUser(id);
        return new ResponseEntity(new StatusResponseEntity(true, "User Find Successful", convertToDto(user)), HttpStatus.OK);
    }

    /**
     * Update user password
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/users/{id}/update/password")
    public ResponseEntity<StatusResponseEntity<?>> updateUserPassword(@PathVariable Long id, @Valid @RequestBody PasswordUpdateRequest request) {

        // get prinicple obect from auth header
        Object prinicipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // check if password matches by using password encoder, if so, set new password and save user
        if (prinicipal instanceof UserPrincipal) {
            if (((UserPrincipal) prinicipal).getId().equals(id)) {
                User user = userService.findUser(id);
                if (passwordEncoder.matches(request.getCurrentPassword(), user.getUserPassword())) {
                    user.setUserPassword(passwordEncoder.encode(request.getNewPassword()));
                    userService.saveUser(user);
                    return new ResponseEntity(new StatusResponseEntity(true, "Password Update Successful", convertToDto(user)), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new StatusResponseEntity(true, "Password Incorrect", false), HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity(new StatusResponseEntity(false, "Unauthorized Request", false), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity(new StatusResponseEntity(false, "Service Unavailable", false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update user details
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/users/{id}/update")
    public ResponseEntity<StatusResponseEntity<?>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO user) {
        User foundUser = userService.findUser(id);

        if (user.getUserFirstName() != null) {
            foundUser.setUserFirstName(user.getUserFirstName());
        }
        if (user.getUserLastName() != null) {
            foundUser.setUserLastName(user.getUserLastName());
        }
        if (user.getUserEmail() != null) {
            foundUser.setUserEmail(user.getUserEmail());
        }
        if (user.getUserDob() != null) {
            foundUser.setUserDob(user.getUserDob());
        }
        if (user.getUserBio() != null) {
            foundUser.setUserBio(user.getUserBio());
        }
        userService.saveUser(foundUser);
        UserDTO userDTO = convertToDto(foundUser);
        return new ResponseEntity(new StatusResponseEntity(true, "User Update Successful", userDTO), HttpStatus.OK);
    }

    /**
     * Update user details with profile image
     * @param id
     * @param user
     * @param file
     * @return
     */
    @PostMapping("/users/{id}/update")
    public ResponseEntity<StatusResponseEntity<?>> updateUserWPhoto(@PathVariable Long id, @Valid @RequestPart(value = "user", required = false) UserDTO user, @RequestPart(value = "file", required = false) MultipartFile file) {
        User foundUser = userService.findUser(id);

        if (user != null) {
            if (user.getUserFirstName() != null) {
                foundUser.setUserFirstName(user.getUserFirstName());
            }
            if (user.getUserLastName() != null) {
                foundUser.setUserLastName(user.getUserLastName());
            }
            if (user.getUserEmail() != null) {
                foundUser.setUserEmail(user.getUserEmail());
            }
            if (user.getUserDob() != null) {
                foundUser.setUserDob(user.getUserDob());
            }
            if (user.getUserBio() != null) {
                foundUser.setUserBio(user.getUserBio());
            }
        }

        // upload image and return response, create photograph object, associate user and returned filepath
        if (file != null) {
            ImageUploadResponse imageUploadResponse = imageUploadService.uploadFileRequest(file);

            if (imageUploadResponse.getSuccess()) {
                UserPhotograph photograph = new UserPhotograph();
                photograph.setPhotoName(file.getOriginalFilename());
                photograph.setPhotoPath(imageUploadResponse.getFilepath());
                photograph.setActive(true);
                photograph.setEntity(foundUser);

                if (foundUser.getUserPhotographs() == null) {
                    foundUser.setUserPhotographs(new ArrayList<UserPhotograph>());
                }
                // set all old photos to inactive
                for (Photograph photographThru : foundUser.getUserPhotographs()) {
                    photographThru.setActive(false);
                }
                // add photograph to user
                foundUser.getUserPhotographs().add(photograph);
            } else {
                userService.saveUser(foundUser);
                UserDTO userDTO = convertToDto(foundUser);
                return new ResponseEntity(new StatusResponseEntity(true, "User Update Partial Success. Image Update Failure", userDTO), HttpStatus.PARTIAL_CONTENT);
            }

        }
        userService.saveUser(foundUser);
        UserDTO userDTO = convertToDto(foundUser);
        return new ResponseEntity(new StatusResponseEntity(true, "User Update Successful", userDTO), HttpStatus.OK);
    }

    /**
     * Conver User to UserDTO
     * @param user
     * @return
     */
    private UserDTO convertToDto(User user) {
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        return userDto;
    }


}
