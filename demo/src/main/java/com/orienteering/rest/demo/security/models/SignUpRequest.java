package com.orienteering.rest.demo.security.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Signup request holds signup information including user details, email address and password
 */
public class SignUpRequest {

    // first name of user
    @NotBlank
    @Size(min = 1,max = 20)
    private String firstName;
    // last name of user
    @NotBlank
    @Size(min = 1,max = 20)
    private String lastName;
    // email address of user
    @NotBlank
    @Size(min = 1,max = 40)
    @Email
    private String email;
    // unhashed plain text password
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    // user date of birth
    @Past
    private Date userDob;
    // user bio
    @Size(min = 1, max = 100)
    private String userBio;

    /**
     * default constructor
     */
    public SignUpRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUserDob() {
        return userDob;
    }

    public void setUserDob(Date userDob) {
        this.userDob = userDob;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }
}
