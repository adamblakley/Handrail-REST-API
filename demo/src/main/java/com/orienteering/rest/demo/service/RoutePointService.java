package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.RoutePoint;
import com.orienteering.rest.demo.repository.RoutePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoutePointService {

    public RoutePointService() {
    }

    @Autowired
    RoutePointRepository routePointRepository;

    public List<RoutePoint> findAllRoutePoints(){
        List<RoutePoint> routePoints = new ArrayList<RoutePoint>();
        routePointRepository.findAll().forEach(routePoint -> routePoints.add(routePoint));
        return routePoints;
    }

    public void saveRoutePoint(RoutePoint routePoint) { routePointRepository.save(routePoint);
    }

    public RoutePoint findRoutePoint(Integer id){
        return routePointRepository.findById(id).get();
    }

    public void deleteRoutePoint(Integer id){
        routePointRepository.deleteById(id);
    }
}
