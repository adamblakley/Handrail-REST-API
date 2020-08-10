package com.orienteering.rest.demo.security.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Class representing password update request
 */
public class PasswordUpdateRequest {

    @NotBlank
    @Size(min = 6, max = 20)
    private String currentPassword;

    @NotBlank
    @Size(min = 6, max = 20)
    private String newPassword;

    public PasswordUpdateRequest() {
    }

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
