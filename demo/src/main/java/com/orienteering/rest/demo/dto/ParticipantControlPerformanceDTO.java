package com.orienteering.rest.demo.dto;

public class ParticipantControlPerformanceDTO {

    private Integer pcpId;

    private Long controlTime;

    private ControlDTO pcpControl;

    public ParticipantControlPerformanceDTO() {
        super();
    }

    public ParticipantControlPerformanceDTO(Integer pcpId, Long controlTime, UserDTO pcpUser, ControlDTO pcpControl, EventDTO pcpEvent) {
        this.pcpId = pcpId;
        this.controlTime = controlTime;
        this.pcpControl = pcpControl;
    }

    public Integer getPcpId() {
        return pcpId;
    }

    public void setPcpId(Integer pcpId) {
        this.pcpId = pcpId;
    }

    public Long getControlTime() {
        return controlTime;
    }

    public void setControlTime(Long controlTime) {
        this.controlTime = controlTime;
    }


    public ControlDTO getPcpControl() {
        return pcpControl;
    }

    public void setPcpControl(ControlDTO pcpControl) {
        this.pcpControl = pcpControl;
    }

    @Override
    public String toString() {
        return "ParticipantControlPerformanceDTO{" +
                "pcpId=" + pcpId +
                ", controlTime=" + controlTime +
                ", pcpControl=" + pcpControl +
                '}';
    }
}
