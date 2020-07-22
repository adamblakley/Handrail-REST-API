package com.orienteering.rest.demo.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orienteering.rest.demo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserPrincipal returned from UserDetailsService
 * Information store for authentication/authorization
 */
public class UserPrincipal implements UserDetails {

    /**
     * variables
     */
    private Integer id;
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
    public UserPrincipal(Integer id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     *
     * @param user
     * @return
     */
    public static UserPrincipal create(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getUserId(),user.getUserEmail(),user.getUserPassword(),authorities
        );
    }

    public static UserPrincipal create(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getUserId(),user.getUserEmail(),user.getUserPassword(),authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
}
