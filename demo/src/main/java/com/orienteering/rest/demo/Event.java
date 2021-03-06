package com.orienteering.rest.demo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.orienteering.rest.demo.dto.ParticipantControlPerformanceDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Integer eventID;
    private String eventName;
    private Date eventCreated;

    private Date eventDate;

    private String eventNote;
    private Integer eventStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course eventCourse;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User eventOrganiser;

    @JsonManagedReference
    @OneToMany(mappedBy = "participantEvent", cascade = CascadeType.PERSIST)
    private List<Participant> participants;


    public Event() {
        super();
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventCreated() {
        return eventCreated;
    }

    public void setEventCreated(Date eventCreated) {
        this.eventCreated = eventCreated;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public Integer getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Course getEventCourse() {
        return eventCourse;
    }

    public void setEventCourse(Course eventCourse) {
        this.eventCourse = eventCourse;
    }

    public Integer getCourseID(){
        return eventCourse.getCourseId();
    }


    public User getEventOrganiser() {
        return eventOrganiser;
    }

    public void setEventOrganiser(User eventOrganiser) {
        this.eventOrganiser = eventOrganiser;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant participant){
        this.participants.add(participant);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", eventName='" + eventName + '\'' +
                ", eventCreated=" + eventCreated +
                ", eventDate=" + eventDate +
                ", eventNote='" + eventNote + '\'' +
                ", eventStatus=" + eventStatus +
                ", eventCourse=" + eventCourse +
                '}';
    }
}


