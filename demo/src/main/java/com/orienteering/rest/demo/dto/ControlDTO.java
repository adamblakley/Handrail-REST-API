package com.orienteering.rest.demo.dto;

import com.orienteering.rest.demo.ControlPhotograph;

public class ControlDTO {

    private Integer controlId;
    private Integer controlPosition;
    private String controlName;
    private String controlNote;
    private Long controlTime;
    private Double controlLatitude;
    private Double controlLongitude;
    private Double controlAltitude;

    private PhotographDTO controlPhotograph;

    public ControlDTO() {
    }

    public ControlDTO(Integer controlId, String controlName, String controlNote, Long controlTime, Double controlLatitude, Double controlLongitude, Double controlAltitude, PhotographDTO controlPhotograph) {
        this.controlId = controlId;
        this.controlName = controlName;
        this.controlNote = controlNote;
        this.controlTime = controlTime;
        this.controlLatitude = controlLatitude;
        this.controlLongitude = controlLongitude;
        this.controlAltitude = controlAltitude;
        this.controlPhotograph = controlPhotograph;
    }

    public Integer getControlPosition() {
        return controlPosition;
    }

    public void setControlPosition(Integer controlPosition) {
        this.controlPosition = controlPosition;
    }

    public Integer getControlId() {
        return controlId;
    }

    public void setControlId(Integer controlId) {
        this.controlId = controlId;
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

    public Long getControlTime() {
        return controlTime;
    }

    public void setControlTime(Long controlTime) {
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

    public PhotographDTO getControlPhotograph() {
        return controlPhotograph;
    }

    public void setControlPhotograph(PhotographDTO controlPhotograph) {
        this.controlPhotograph = controlPhotograph;
    }

    @Override
    public String toString() {
        return "ControlDTO{" +
                "controlId=" + controlId +
                ", controlPosition=" + controlPosition +
                ", controlName='" + controlName + '\'' +
                ", controlNote='" + controlNote + '\'' +
                ", controlTime=" + controlTime +
                ", controlLatitude=" + controlLatitude +
                ", controlLongitude=" + controlLongitude +
                ", controlAltitude=" + controlAltitude +
                ", controlPhotograph=" + controlPhotograph +
                '}';
    }
}
