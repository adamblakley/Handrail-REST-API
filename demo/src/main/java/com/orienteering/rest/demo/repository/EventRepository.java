package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.Event;
import com.orienteering.rest.demo.Participant;
import com.orienteering.rest.demo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

    @Query
    public List<Event> findByIsActiveTrue();

    @Query
    public List<Event> findByParticipants_ParticipantUser(User user);
}
