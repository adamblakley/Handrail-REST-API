package com.orienteering.rest.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class RoutePoint {

    @Id
    @GeneratedValue
    private Integer routePointId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant routePointParticipant;

    private Integer routePointPosition;
    private Double routePointLatitude;
    private Double routePointLongitude;



    public RoutePoint() {
        super();
    }



    public Integer getRoutePointId() {
        return routePointId;
    }

    public void setRoutePointId(Integer routePointId) {
        this.routePointId = routePointId;
    }

    public Integer getRoutePointPosition() {
        return routePointPosition;
    }

    public void setRoutePointPosition(Integer routePointPosition) {
        this.routePointPosition = routePointPosition;
    }

    public Double getRoutePointLatitude() {
        return routePointLatitude;
    }

    public void setRoutePointLatitude(Double routePointLatitude) {
        this.routePointLatitude = routePointLatitude;
    }

    public Double getRoutePointLongitude() {
        return routePointLongitude;
    }

    public void setRoutePointLongitude(Double routePointLongitude) {
        this.routePointLongitude = routePointLongitude;
    }

    public Participant getRoutePointParticipant() {
        return routePointParticipant;
    }

    public void setRoutePointParticipant(Participant routePointParticipant) {
        this.routePointParticipant = routePointParticipant;
    }

    @Override
    public String toString() {
        return "RoutePoint{" +
                "routePointId=" + routePointId +
                ", routePointPosition=" + routePointPosition +
                ", routePointLatitude=" + routePointLatitude +
                ", routePointLongitude=" + routePointLongitude +
                ", routePointParticipant=" + routePointParticipant +
                '}';
    }
}
