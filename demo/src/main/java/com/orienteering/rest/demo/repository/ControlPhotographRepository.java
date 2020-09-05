package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.model.ControlPhotograph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *Repository for ControlPhotograph objects
 */
@Repository
public interface ControlPhotographRepository extends JpaRepository<ControlPhotograph, Long> {



}
