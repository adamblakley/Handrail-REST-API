package com.orienteering.rest.demo.dto;
import com.orienteering.rest.demo.EventPhotograph;

import java.util.Date;
import java.util.List;

public class EventDTO {

    private Integer eventId;
    private String eventName;
    private Date eventCreated;
    private UserDTO eventOrganiser;
    private Date eventDate;
    private String eventNote;
    private Integer eventStatus;
    private CourseDTO eventCourse;
    private List<ParticipantDTO> participants;
    private PhotographDTO eventPhotograph;

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

    public PhotographDTO getEventPhotograph() {
        return eventPhotograph;
    }

    public void setEventPhotograph(PhotographDTO eventPhotograph) {
        this.eventPhotograph = eventPhotograph;
    }


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
                ", eventPhotograph=" + eventPhotograph +
                '}';
    }
}
