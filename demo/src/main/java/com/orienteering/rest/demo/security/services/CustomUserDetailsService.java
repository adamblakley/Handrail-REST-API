package com.orienteering.rest.demo.security.services;

import com.orienteering.rest.demo.User;
import com.orienteering.rest.demo.repository.UserRepository;
import com.orienteering.rest.demo.security.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/***
 * Service for user details UserPrincipal
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * User Repository for users
     */
    @Autowired
    UserRepository userRepository;

    /***
     * User login with email
     * @param userEmail
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        try{
            User user = userRepository.findByEmail(userEmail);
            return UserPrincipal.create(user);
        } catch (UsernameNotFoundException u){
            throw new UsernameNotFoundException("User not found with email : "+userEmail);
        }
    }

    /**
     * Access user by id
     * @param userId
     * @return
     */
    public UserDetails loadUserById(Integer userId){
        try{
            Optional<User> user = userRepository.findById(userId);
            return UserPrincipal.create(user);
        } catch( UsernameNotFoundException u){
            throw new UsernameNotFoundException("User not found with id : "+userId);
        }
    }
}
