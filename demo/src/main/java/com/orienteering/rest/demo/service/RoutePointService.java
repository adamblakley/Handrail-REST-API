package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.RoutePoint;
import com.orienteering.rest.demo.repository.RoutePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Service queries repository for RoutePoint objects
 */
@Service
public class RoutePointService {

    /**
     * default constructor
     */
    public RoutePointService() {
    }
    // Repository to request database queries
    @Autowired
    RoutePointRepository routePointRepository;

    /**
     * find all route points
     * @return
     */
    public List<RoutePoint> findAllRoutePoints(){
        List<RoutePoint> routePoints = new ArrayList<RoutePoint>();
        routePointRepository.findAll().forEach(routePoint -> routePoints.add(routePoint));
        return routePoints;
    }

    /**
     * save route points
     * @param routePoint
     */
    public void saveRoutePoint(RoutePoint routePoint) { routePointRepository.save(routePoint);
    }

    /**
     * find route point by id
     * @param id
     * @return
     */
    public RoutePoint findRoutePoint(Integer id){
        return routePointRepository.findById(id).get();
    }

    /**
     * delete route point
     * @param id
     */
    public void deleteRoutePoint(Integer id){
        routePointRepository.deleteById(id);
    }
}
