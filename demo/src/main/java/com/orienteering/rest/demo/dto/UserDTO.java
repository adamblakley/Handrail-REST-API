package com.orienteering.rest.demo.dto;

import com.orienteering.rest.demo.Event;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

public class UserDTO {


    private Long userId;

    private String userEmail;

    private String userFirstName;

    private String userLastName;

    private Date userDob;

    private String userBio;

    private PhotographDTO userPhotograph;

    public UserDTO() {
    }

    public UserDTO(Long userId, String userEmail, String userFirstName, String userLastName, Date userDob, String userBio, PhotographDTO userPhotograph) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDob = userDob;
        this.userBio = userBio;
        this.userPhotograph = userPhotograph;
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

    public PhotographDTO getUserPhotograph() {
        return userPhotograph;
    }

    public void setUserPhotograph(PhotographDTO userPhotograph) {
        this.userPhotograph = userPhotograph;
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
                ", userPhotograph=" + userPhotograph +
                '}';
    }
}
