package com.orienteering.rest.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.orienteering.rest.demo.model.Participant;

import javax.persistence.*;

/**
 * Represents a lat/lng of a point in a participant orienteers route through an event
 */
@Entity
public class RoutePoint {
    // Route Point ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routePointId;
    // Route Point Participant
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant routePointParticipant;
    // Route Point Position
    private Integer routePointPosition;
    // Route Point Latitude
    private Double routePointLatitude;
    // Route Point Longitude
    private Double routePointLongitude;

    /**
     * Default constuctor
     */
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
