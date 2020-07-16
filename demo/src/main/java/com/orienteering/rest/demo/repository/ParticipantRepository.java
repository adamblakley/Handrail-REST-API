package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Integer> {
}
