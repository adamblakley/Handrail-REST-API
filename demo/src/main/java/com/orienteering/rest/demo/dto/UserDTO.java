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

    private Date userCreated;

    private String userEmail;

    private String userPassword;

    private String userFirstName;

    private String userLastName;

    private Date userDob;

    private String userBio;

    private int userType;

    private PhotographDTO userPhotograph;

    public UserDTO() {
    }

    public UserDTO(Long userId, Date userCreated, String userEmail, String userPassword, String userFirstName, String userLastName, Date userDob, String userBio, int userType, PhotographDTO userPhotograph) {
        this.userId = userId;
        this.userCreated = userCreated;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDob = userDob;
        this.userBio = userBio;
        this.userType = userType;
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

    public Date getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Date userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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
                ", userCreated=" + userCreated +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userDob=" + userDob +
                ", userBio='" + userBio + '\'' +
                ", userType=" + userType +
                ", userPhotograph=" + userPhotograph +
                '}';
    }
}
