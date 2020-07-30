package com.orienteering.rest.demo.security.payloads;

import javax.validation.constraints.NotBlank;

/**
 * payload for user loginrequest contains userEmail and password
 */
public class LoginRequest {

    @NotBlank
    private String userEmail;
    @NotBlank
    private String password;

    /**
     * default constructor
     */
    public LoginRequest() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
