package com.orienteering.rest.demo.security.payloads;

import javax.validation.constraints.NotBlank;

/**
 * payload for user loginrequest contains userEmail and password
 */
public class LoginRequest {

    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;

    /**
     * default constructor
     */
    public LoginRequest() {
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
