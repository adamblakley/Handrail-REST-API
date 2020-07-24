package com.orienteering.rest.demo.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orienteering.rest.demo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * UserPrincipal returned from UserDetailsService
 * Information store for authentication/authorization
 */
public class UserPrincipal implements UserDetails {

    /**
     * variables
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
     *
     * @param user
     * @return
     */
    public static UserPrincipal create(User user){
        List<GrantedAuthority> authorities = user.getUserRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getERole().name())
        ).collect(Collectors.toList());
        UserPrincipal userPrincipal = new UserPrincipal(
                user.getUserId().longValue(),user.getUserEmail(),user.getUserPassword(),authorities
        );
        return userPrincipal;
    }

    /**
     * returns boolean if passed object == to this
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if (this==o) {
            return true;
        } else if (o==null || getClass() !=o.getClass()){
            return false;
        } else {
            //cast o to UserPrincipal, compare id
            UserPrincipal userPrin = (UserPrincipal) o;
            return Objects.equals(id, userPrin.id);
        }
    }

    /**
     * return hash id
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
