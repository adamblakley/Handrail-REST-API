package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.model.Photograph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographRepository extends JpaRepository<Photograph,Long> {
}
