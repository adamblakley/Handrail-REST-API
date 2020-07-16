package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.ControlPhotograph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlPhotographRepository extends JpaRepository<ControlPhotograph, Integer> {
}
