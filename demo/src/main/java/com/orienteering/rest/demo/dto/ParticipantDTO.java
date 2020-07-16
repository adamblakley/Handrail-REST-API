package com.orienteering.rest.demo.dto;
import java.util.List;

public class ParticipantDTO {

    private Integer participantId;

    private UserDTO participantUser;


    private List<ParticipantControlPerformanceDTO> participantControlPerformances;

    private List<RoutePointDTO> routePoints;

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
