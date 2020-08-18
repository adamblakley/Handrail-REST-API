package com.orienteering.rest.demo.model;

import com.orienteering.rest.demo.dto.ControlDTO;
import com.orienteering.rest.demo.dto.ParticipantControlPerformanceDTO;
import com.orienteering.rest.demo.dto.RoutePointDTO;

import java.util.List;

/**
 * Performance Response holds all performance information to be consumed in client performance record
 */
public class PerformanceResponse {

    List<ControlDTO> controls;
    List<ParticipantControlPerformanceDTO> performances;
    List<RoutePointDTO> routePoints;

    public PerformanceResponse() {
    }

    public PerformanceResponse(List<ControlDTO> controls, List<ParticipantControlPerformanceDTO> performances, List<RoutePointDTO> routePoints) {
        this.controls = controls;
        this.performances = performances;
        this.routePoints = routePoints;
    }

    public List<ControlDTO> getControls() {
        return controls;
    }

    public void setControls(List<ControlDTO> controls) {
        this.controls = controls;
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
