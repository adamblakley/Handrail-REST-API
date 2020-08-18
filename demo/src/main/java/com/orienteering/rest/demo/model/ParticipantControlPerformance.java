package com.orienteering.rest.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ParticipantControlPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pcpId;

    private Long controlTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_id")
    private Control pcpControl;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="participant_id")
    private Participant pcpParticipant;


    public ParticipantControlPerformance() {
        super();
    }

    public ParticipantControlPerformance(Long controlTime, User user, Control pcpControl) {
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


    public Control getPcpControl() {
        return pcpControl;
    }

    public Participant getPcpParticipant() {
        return pcpParticipant;
    }


    public void setPcpParticipant(Participant pcpParticipant) {
        this.pcpParticipant = pcpParticipant;
    }

    public void setPcpControl(Control pcpControl) {
        this.pcpControl = pcpControl;
    }




    @Override
    public String toString() {
        return "ParticipantControlPerformance{" +
                "pcpId=" + pcpId +
                ", controlTime=" + controlTime +
                ", pcpControl=" + pcpControl +
                '}';
    }
}
