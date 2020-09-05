package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.model.RoutePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *Repository for RoutePoint objects
 */
@Repository
public interface RoutePointRepository extends JpaRepository<RoutePoint,Integer> {
}
