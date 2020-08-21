package com.orienteering.rest.demo.security.controllers;

import com.orienteering.rest.demo.model.ERole;
import com.orienteering.rest.demo.model.Role;
import com.orienteering.rest.demo.model.StatusResponseEntity;
import com.orienteering.rest.demo.model.User;
import com.orienteering.rest.demo.repository.RoleRepository;
import com.orienteering.rest.demo.repository.UserRepository;
import com.orienteering.rest.demo.security.JwtTokenProvider;
import com.orienteering.rest.demo.security.exceptions.InternalServerException;
import com.orienteering.rest.demo.security.models.UserPrincipal;
import com.orienteering.rest.demo.security.responses.JwtAuthenticationResponse;
import com.orienteering.rest.demo.security.models.LoginRequest;
import com.orienteering.rest.demo.security.models.SignUpRequest;
import com.orienteering.rest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

/**
 * Class handles the authentication endpoints and logic for user signup, login and login status
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    /**
     * Login post mapping returns a jwt authentication response
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<StatusResponseEntity<?>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserPrincipal user = (UserPrincipal) obj;
        return  new ResponseEntity( new StatusResponseEntity(true, "User login successful",new JwtAuthenticationResponse(jwt,user.getId())),HttpStatus.OK);

    }

    /**
     * Check if the user is logged in
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<StatusResponseEntity<Boolean>> loggedInUser(HttpServletRequest httpServletRequest){
        Object prinicipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (prinicipal instanceof UserPrincipal && checkLogin((UserPrincipal)prinicipal)){
            return new ResponseEntity(new StatusResponseEntity(true, "User logged in", true),HttpStatus.OK);
        }
        return new ResponseEntity(new StatusResponseEntity(false, "User not logged in", false),HttpStatus.FORBIDDEN);
    }


    /**
     * Returns Boolean of current login userprincipal
     * @param userPrincipal
     * @return
     */
    public Boolean checkLogin(UserPrincipal userPrincipal){
        if (userPrincipal.isAccountNonExpired()&&userPrincipal.isAccountNonLocked()&&userPrincipal.isCredentialsNonExpired()&&userPrincipal.isEnabled()){
            return true;
        }
        else return false;
    }


    /**
     * Signup request returns negative response if exists, creates new user otherwise, returns positive response
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<StatusResponseEntity<Boolean>> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        if(userRepository.existsByUserEmail(signUpRequest.getEmail())){
            return new ResponseEntity(new StatusResponseEntity(false, "User Email already exists", false), HttpStatus.CONFLICT);
        } else {
            User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(),signUpRequest.getEmail(),signUpRequest.getPassword(),signUpRequest.getUserDob(),signUpRequest.getUserBio());
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            Role role = roleRepository.findByRole(ERole.ROLE_USER).orElseThrow(()->new InternalServerException("Role not set"));
            user.setUserRoles(Collections.singleton(role));
            User savedUser = userService.saveUser(user);
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{id}").buildAndExpand(savedUser.getUserId()).toUri();
            return new ResponseEntity(new StatusResponseEntity(true, "Successfully registered User", true), HttpStatus.CREATED);
        }
    }

}
