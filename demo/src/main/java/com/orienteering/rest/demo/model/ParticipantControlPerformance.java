package com.orienteering.rest.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Represents the time a participant registered at a control
 */
@Entity
public class ParticipantControlPerformance {
    // pcp id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pcpId;
    // control time
    private Long controlTime;
    // associated control
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_id")
    private Control pcpControl;
    // associated participant
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="participant_id")
    private Participant pcpParticipant;

    /**
     * Default constuctor
     */
    public ParticipantControlPerformance() {
        super();
    }

    /**
     * Constructor with args
     * @param controlTime
     * @param user
     * @param pcpControl
     */
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
