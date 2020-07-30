package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.EventPhotograph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPhotographRepository extends JpaRepository<EventPhotograph,Long> {
}
