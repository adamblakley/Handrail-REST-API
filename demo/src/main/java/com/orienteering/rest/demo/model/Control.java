package com.orienteering.rest.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Control object represents orienteering control
 */
@Entity
public class Control {
    // control id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer controlId;
    // control position
    private Integer controlPosition;
    // control name
    private String controlName;
    // control note
    private String controlNote;
    // control time
    private String controlTime;
    // control latitude
    private Double controlLatitude;
    // control longitude
    private Double controlLongitude;
    // control altitude
    private Double controlAltitude;
    // control completed check
    private boolean controlCompleted;
    // course for control
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Course controlCourse;
    //control photographs
    @JsonManagedReference
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    private List<ControlPhotograph> controlPhotographs;
    // performances for control
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pcpControl", cascade = CascadeType.ALL)
    private List<ParticipantControlPerformance> participantControlPerformances;
    // default constructor
    public Control(){
        super();
    }

    public Integer getControlId() {
        return controlId;
    }

    public void setControlId(Integer controlId) {
        this.controlId = controlId;
    }

    public Integer getControlPosition() {
        return controlPosition;
    }

    public void setControlPosition(Integer controlPosition) {
        this.controlPosition = controlPosition;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getControlNote() {
        return controlNote;
    }

    public void setControlNote(String controlNote) {
        this.controlNote = controlNote;
    }

    public String getControlTime() {
        return controlTime;
    }

    public void setControlTime(String controlTime) {
        this.controlTime = controlTime;
    }

    public Double getControlLatitude() {
        return controlLatitude;
    }

    public void setControlLatitude(Double controlLatitude) {
        this.controlLatitude = controlLatitude;
    }

    public Double getControlLongitude() {
        return controlLongitude;
    }

    public void setControlLongitude(Double controlLongitude) {
        this.controlLongitude = controlLongitude;
    }

    public Double getControlAltitude() {
        return controlAltitude;
    }

    public void setControlAltitude(Double controlAltitude) {
        this.controlAltitude = controlAltitude;
    }

    public boolean isControlCompleted() {
        return controlCompleted;
    }

    public void setControlCompleted(boolean controlCompleted) {
        this.controlCompleted = controlCompleted;
    }

    public Course getControlCourse() {
        return controlCourse;
    }

    public void setControlCourse(Course controlCourse) {
        this.controlCourse = controlCourse;
    }

    public List<ParticipantControlPerformance> getParticipantControlPerformances() {
        return participantControlPerformances;
    }

    public void setParticipantControlPerformances(List<ParticipantControlPerformance> participantControlPerformances) {
        this.participantControlPerformances = participantControlPerformances;
    }

    public List<ControlPhotograph> getControlPhotographs() {
        return controlPhotographs;
    }

    public void setControlPhotographs(List<ControlPhotograph> controlPhotographs) {
        this.controlPhotographs = controlPhotographs;
    }

    @Override
    public String toString() {
        return "Control{" +
                "controlId=" + controlId +
                ", controlPosition=" + controlPosition +
                ", controlName='" + controlName + '\'' +
                ", controlNote='" + controlNote + '\'' +
                ", controlTime=" + controlTime +
                ", controlLatitude=" + controlLatitude +
                ", controlLongitude=" + controlLongitude +
                ", controlAltitude=" + controlAltitude +
                ", controlCompleted=" + controlCompleted +
                ", controlPhotograph=" + controlPhotographs +
                '}';
    }
}
