package com.orienteering.rest.demo.security.controllers;

import com.orienteering.rest.demo.ERole;
import com.orienteering.rest.demo.Role;
import com.orienteering.rest.demo.User;
import com.orienteering.rest.demo.repository.RoleRepository;
import com.orienteering.rest.demo.repository.UserRepository;
import com.orienteering.rest.demo.security.JwtTokenProvider;
import com.orienteering.rest.demo.security.exceptions.AppException;
import com.orienteering.rest.demo.security.payloads.APIResponse;
import com.orienteering.rest.demo.security.payloads.JwtAuthenticationResponse;
import com.orienteering.rest.demo.security.payloads.LoginRequest;
import com.orienteering.rest.demo.security.payloads.SignUpRequest;
import com.orienteering.rest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

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
     * Login post mapping returns jwt token as response
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.getUsernameOrEmail()+loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return  ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    /**
     * Signup request returns negative response if exists, creates new user otherwise, returns positive response
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<APIResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        if(userRepository.existsByUserEmail(signUpRequest.getEmail())){
            return new ResponseEntity(new APIResponse(false,"Email already exists"),
                    HttpStatus.BAD_REQUEST);
        } else {
            User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName(),signUpRequest.getEmail(),signUpRequest.getPassword(),signUpRequest.getUserDob(),signUpRequest.getUserBio());
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            Role role = roleRepository.findByRole(ERole.ROLE_USER).orElseThrow(()->new AppException("Role not set"));
            user.setUserRoles(Collections.singleton(role));
            User savedUser = userService.saveUser(user);

            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{id}").buildAndExpand(savedUser.getUserId()).toUri();
            return ResponseEntity.created(location).body(new APIResponse(true,"User registered"));
        }
    }

}
