package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.model.Course;
import com.orienteering.rest.demo.model.Event;
import com.orienteering.rest.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *Repository for Event objects
 */
@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

    /**
     * Query Events by active status
     * @return
     */
    @Query
    public List<Event> findByIsActiveTrue();

    /**
     * Query Events by active status
     * @return
     */
    @Query
    public List<Event> findByIsActiveTrueOrderByEventDateDesc();

    /**
     * Query Events by participant user
     * @param user
     * @return
     */
    @Query
    public List<Event> findByParticipants_ParticipantUser(User user);

    /**
     * Query events by organiser and active status
     * @param user
     * @return
     */
    @Query
    public List<Event> findByEventOrganiserAndIsActiveTrue(User user);

    /**
     * Query events by organiser only
     * @param user
     * @return
     */
    @Query
    public List<Event> findByEventOrganiser(User user);
}
