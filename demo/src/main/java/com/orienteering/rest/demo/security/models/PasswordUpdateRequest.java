package com.orienteering.rest.demo.security.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Class representing password update request
 */
public class PasswordUpdateRequest {

    // old user password
    @NotBlank
    @Size(min = 6, max = 20)
    private String currentPassword;

    //new user password
    @NotBlank
    @Size(min = 6, max = 20)
    private String newPassword;

    /**
     * default constructor
     */
    public PasswordUpdateRequest() {
    }

    /**
     * constructor with arguments
     * @param currentPassword
     * @param newPassword
     */
    public PasswordUpdateRequest(@NotBlank @Size(min = 6, max = 20) String currentPassword, @NotBlank @Size(min = 6, max = 20) String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
