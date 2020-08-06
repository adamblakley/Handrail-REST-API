package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

    @Query
    public List<Event> findByIsActiveTrue();
}
