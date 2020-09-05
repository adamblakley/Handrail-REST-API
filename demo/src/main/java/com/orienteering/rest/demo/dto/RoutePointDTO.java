package com.orienteering.rest.demo.dto;

/**
 * DTO For RoutePoint Object
 */
public class RoutePointDTO {
    // Route Point ID
    private Integer routePointId;
    // Route Point Position
    private Integer routePointPosition;
    // Route Point Latitude
    private Double routePointLatitude;
    // Route Point Longitude
    private Double routePointLongitude;

    /**
     * Default Constructor
     */
    public RoutePointDTO() {
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

    @Override
    public String toString() {
        return "RoutePointDTO{" +
                "routePointId=" + routePointId +
                ", routePointPosition=" + routePointPosition +
                ", routePointLatitude=" + routePointLatitude +
                ", routePointLongitude=" + routePointLongitude +
                '}';
    }
}
