package com.orienteering.rest.demo.dto;
import java.util.List;

/**
 * DTO for Participant Object
 */
public class ParticipantDTO {

    // Participant ID
    private Integer participantId;
    // Participant User
    private UserDTO participantUser;
    // Participant PCPS
    private List<ParticipantControlPerformanceDTO> participantControlPerformances;
    // Participant RoutePoints
    private List<RoutePointDTO> routePoints;
    // Participant Position
    private int position;

    /**
     * Default Constructor
     */
    public ParticipantDTO() {
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public UserDTO getParticipantUser() {
        return participantUser;
    }

    public void setParticipantUser(UserDTO participantUser) {
        this.participantUser = participantUser;
    }

    public List<ParticipantControlPerformanceDTO> getParticipantControlPerformances() {
        return participantControlPerformances;
    }

    public void setParticipantControlPerformances(List<ParticipantControlPerformanceDTO> participantControlPerformances) {
        this.participantControlPerformances = participantControlPerformances;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<RoutePointDTO> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePointDTO> routePoints) {
        this.routePoints = routePoints;
    }

    @Override
    public String toString() {
        return "ParticipantDTO{" +
                "participantId=" + participantId +
                ", participantUser=" + participantUser +

                ", participantControlPerformances=" + participantControlPerformances +
                ", routePoints=" + routePoints +
                '}';
    }
}
