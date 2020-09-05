package com.orienteering.rest.demo.dto;

import java.util.Date;
import java.util.List;

/**
 * DTO for User Object
 */
public class UserDTO {
    // user id
    private Long userId;
    // user email
    private String userEmail;
    // user first name
    private String userFirstName;
    // user surname
    private String userLastName;
    // user date of birth
    private Date userDob;
    // user biography
    private String userBio;
    // user photographs
    private List<PhotographDTO> userPhotographs;

    /**
     * Default constructor
     */
    public UserDTO() {
    }

    /**
     * Constructor with args
     * @param userId
     * @param userEmail
     * @param userFirstName
     * @param userLastName
     * @param userDob
     * @param userBio
     * @param userPhotographs
     */
    public UserDTO(Long userId, String userEmail, String userFirstName, String userLastName, Date userDob, String userBio, List<PhotographDTO> userPhotographs) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDob = userDob;
        this.userBio = userBio;
        this.userPhotographs = userPhotographs;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getUserDob() {
        return userDob;
    }

    public void setUserDob(Date userDob) {
        this.userDob = userDob;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public List<PhotographDTO> getUserPhotographs() {
        return userPhotographs;
    }

    public void setUserPhotographs(List<PhotographDTO> userPhotographs) {
        this.userPhotographs = userPhotographs;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userDob=" + userDob +
                ", userBio='" + userBio + '\'' +
                ", userPhotograph=" + userPhotographs +
                '}';
    }
}
