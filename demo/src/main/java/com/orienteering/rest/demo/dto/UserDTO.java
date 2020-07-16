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

    public UserDTO() {
        super();
    }

    public UserDTO(String userEmail, String userPassword, String userFirstName, String userLastName,Date userDob, String userBio, int userType) {
        super();
        this.userId = userId;
        this.userCreated = userCreated;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDob = userDob;
        this.userBio = userBio;
        this.userType = userType;
    }


    private Integer userId;

    private Date userCreated;

    private String userEmail;

    private String userPassword;

    private String userFirstName;

    private String userLastName;

    private Date userDob;

    private String userBio;

    private int userType;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userCreated=" + userCreated +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userDOB=" + userDob +
                ", userBio='" + userBio + '\'' +
                ", userType=" + userType +
                '}';
    }
}
