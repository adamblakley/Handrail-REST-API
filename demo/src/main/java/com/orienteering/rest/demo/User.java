package com.orienteering.rest.demo;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Date userCreated;

    private String userEmail;

    private String userPassword;

    private String userFirstName;

    private String userLastName;

    @Past
    private Date userDob;

    private String userBio;

    private int userType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "eventOrganiser")
    private List<Event> events;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participantUser", cascade = CascadeType.ALL)
    private List<Participant> participants;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "courseUser")
    private List<Course> courses;

    @JsonManagedReference
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    private List<UserPhotograph> userPhotographs;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Set<Role> userRoles = new HashSet<>();

    /**
     * default constructor
     */
    public User(){
    }

    /**
     * constructor with args
     * @param userEmail
     * @param userPassword
     * @param userFirstName
     * @param userLastName
     * @param userDob
     * @param userBio
     */
    public User(String userFirstName,String userLastName, String userEmail, String userPassword, @Past Date userDob, String userBio) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userDob = userDob;
        this.userBio = userBio;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public List<UserPhotograph> getUserPhotographs() { return userPhotographs; }

    public void setUserPhotographs(List<UserPhotograph> userPhotographs) { this.userPhotographs = userPhotographs; }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userCreated=" + userCreated +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userDob=" + userDob +
                ", userBio='" + userBio + '\'' +
                ", userType=" + userType +
                ", userPhotograph=" + userPhotographs +
                ", userRoles=" + userRoles +
                '}';
    }
}
