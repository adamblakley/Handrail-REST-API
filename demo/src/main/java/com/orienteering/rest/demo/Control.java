package com.orienteering.rest.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Control {

    @Id
    @GeneratedValue
    private Integer controlId;
    private Integer controlPosition;
    private String controlName;
    private String controlNote;
    private String controlTime;
    private Double controlLatitude;
    private Double controlLongitude;
    private Double controlAltitude;
    private boolean controlCompleted;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Course controlCourse;

    @OneToOne(mappedBy = "entity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ControlPhotograph controlPhotograph;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pcpControl", cascade = CascadeType.ALL)
    private List<ParticipantControlPerformance> participantControlPerformances;

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

    public ControlPhotograph getControlPhotograph() {
        return controlPhotograph;
    }

    public void setControlPhotograph(ControlPhotograph controlPhotograph) {
        this.controlPhotograph = controlPhotograph;
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
                ", controlPhotograph=" + controlPhotograph +
                '}';
    }
}
