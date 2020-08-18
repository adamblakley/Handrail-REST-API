package com.orienteering.rest.demo.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orienteering.rest.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * UserPrincipal class returned from the CustomUserDetailsService
 * Acts as the information storage for authentication and authorization
 */
public class UserPrincipal implements UserDetails {

    /**
     * id, username and email associated with a user
     * Password string with Json Ignored
     * Authority collection extending from GrandtedAuthority of Spring Security Framework
     */
    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /***
     * constructor with args
     * @param id
     * @param email
     * @param password
     * @param authorities
     */
    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.username=email;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Create a new user principal from a user class
     * @param user
     * @return
     */
    public static UserPrincipal create(User user){
        List<GrantedAuthority> authorities = user.getUserRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getERole().name())
        ).collect(Collectors.toList());
        UserPrincipal userPrincipal = new UserPrincipal(
                user.getUserId(),user.getUserEmail(),user.getUserPassword(),authorities
        );
        return userPrincipal;
    }

    /**
     * returns boolean value checks object passed is an implementation of userprincipal
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object){
        if (this==object) {
            return true;
        } else if (object==null || getClass() !=object.getClass()){
            return false;
        } else {
            // casts the object to a user principle
            UserPrincipal userPrinciple = (UserPrincipal) object;
            return Objects.equals(id, userPrinciple.id);
        }
    }

    /**
     * returns id hashed
     * @return
     */
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
