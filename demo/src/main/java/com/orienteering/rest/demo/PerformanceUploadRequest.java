package com.orienteering.rest.demo;

import com.orienteering.rest.demo.dto.ParticipantControlPerformanceDTO;
import com.orienteering.rest.demo.dto.RoutePointDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Holds participant performance upload request information
 */
public class PerformanceUploadRequest {

    @NotNull
    private Long startTime;
    @Size(min = 1)
    private List<ParticipantControlPerformanceDTO> performances;
    @Size(min = 1)
    private List<RoutePointDTO> routePoints;

    /**
     * Default constructor
     */
    public PerformanceUploadRequest() {

    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public List<ParticipantControlPerformanceDTO> getPerformances() {
        return performances;
    }

    public void setPerformances(List<ParticipantControlPerformanceDTO> performances) {
        this.performances = performances;
    }

    public List<RoutePointDTO> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePointDTO> routePoints) {
        this.routePoints = routePoints;
    }
}
