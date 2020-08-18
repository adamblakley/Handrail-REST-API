package com.orienteering.rest.demo.security.services;

import com.orienteering.rest.demo.model.User;
import com.orienteering.rest.demo.repository.UserRepository;
import com.orienteering.rest.demo.security.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            User user = userRepository.findByUserEmail(userEmail).orElseThrow(()->{
                return new UsernameNotFoundException("user not found with email: "+userEmail);
            });
            return UserPrincipal.create(user);
    }

    /**
     * Access user by id
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException{
            User user = userRepository.findById(userId).orElseThrow(()->{
                        return new UsernameNotFoundException("user not found with email: "+userId);
            });
            return UserPrincipal.create(user);
    }
}
