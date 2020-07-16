package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.RoutePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutePointRepository extends JpaRepository<RoutePoint,Integer> {
}
