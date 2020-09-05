package com.orienteering.rest.demo.dto;

import java.util.Date;
import java.util.List;

/**
 * DTO for Event Object
 */
public class EventDTO {
    // event id
    private Integer eventId;
    // event name
    private String eventName;
    // date created
    private Date eventCreated;
    // event organiser
    private UserDTO eventOrganiser;
    // event date time
    private Date eventDate;
    // event note
    private String eventNote;
    // event active status
    private Integer eventStatus;
    // event course
    private CourseDTO eventCourse;
    // event participants
    private List<ParticipantDTO> participants;
    // event photographs
    private List<PhotographDTO> eventPhotographs;

    /**
     * Default constructor
     */
    public EventDTO() {
        super();
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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

    public UserDTO getEventOrganiser() {
        return eventOrganiser;
    }

    public void setEventOrganiser(UserDTO eventOrganiser) {
        this.eventOrganiser = eventOrganiser;
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

    public CourseDTO getEventCourse() {
        return eventCourse;
    }

    public void setEventCourse(CourseDTO eventCourse) {
        this.eventCourse = eventCourse;
    }

    public List<ParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }

    public List<PhotographDTO> getEventPhotographs() { return eventPhotographs; }

    public void setEventPhotographs(List<PhotographDTO> eventPhotographs) { this.eventPhotographs = eventPhotographs; }

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventCreated=" + eventCreated +
                ", eventOrganiser=" + eventOrganiser +
                ", eventDate=" + eventDate +
                ", eventNote='" + eventNote + '\'' +
                ", eventStatus=" + eventStatus +
                ", eventCourse=" + eventCourse +
                ", eventPhotograph=" + eventPhotographs +
                '}';
    }
}
