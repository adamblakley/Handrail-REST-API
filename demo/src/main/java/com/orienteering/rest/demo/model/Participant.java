package com.orienteering.rest.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Represents an orienteer participant of an event
 */
@Entity
public class Participant {

    // Participant ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer participantId;
    // Participant User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User participantUser;
    // Participant Position
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "eventid")
    private Event participantEvent;
    // Participant PCPS
    @JsonManagedReference
    @OneToMany(mappedBy = "pcpParticipant", cascade = CascadeType.PERSIST)
    private List<ParticipantControlPerformance> participantControlPerformances;
    // Participant RoutePoints
    @JsonManagedReference
    @OneToMany(mappedBy = "routePointParticipant", cascade = CascadeType.PERSIST)
    private List<RoutePoint> routePoints;

    /**
     * Default constructor
     */
    public Participant() {
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public User getParticipantUser() {
        return participantUser;
    }

    public void setParticipantUser(User participantUser) {
        this.participantUser = participantUser;
    }

    public Event getParticipantEvent() {
        return participantEvent;
    }

    public void setParticipantEvent(Event participantEvent) {
        this.participantEvent = participantEvent;
    }

    public List<ParticipantControlPerformance> getParticipantControlPerformances() {
        return participantControlPerformances;
    }

    public void setParticipantControlPerformances(List<ParticipantControlPerformance> participantControlPerformances) {
        this.participantControlPerformances = participantControlPerformances;
    }

    public List<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePoint> routePoints) {
        this.routePoints = routePoints;
    }

    public Integer getParticipantEventID() { return participantEvent.getEventID(); }

    public void addPcp(ParticipantControlPerformance participantControlPerformance){
        participantControlPerformances.add(participantControlPerformance);
    }
    public void addRoutePoint(RoutePoint routePoint){
        routePoints.add(routePoint);
    }

    /**
     * Calculate total performance time
     * @return
     */
    public Long totalPerformanceTime(){
        // if pcps not empty, ensure last control time is selected, return control time
        if (!this.participantControlPerformances.isEmpty()){
            Long lastPerformanceTime = participantControlPerformances.get(participantControlPerformances.size()-1).getControlTime();

            for (ParticipantControlPerformance performance : participantControlPerformances){
                if (performance.getControlTime()>lastPerformanceTime){
                    lastPerformanceTime=performance.getControlTime();
                }
            }

            return lastPerformanceTime;
        }
        return 0L;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "participantId=" + participantId +
                ", participantUser=" + participantUser +
                ", participantEvent=" + participantEvent +
                ", participantControlPerformances=" + participantControlPerformances +
                ", routePoints=" + routePoints +
                '}';
    }
}
