package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepository extends JpaRepository<Control,Integer> {
}
