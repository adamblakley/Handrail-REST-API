package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.model.ParticipantControlPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *Repository for PCP objects
 */
@Repository
public interface ParticipantControlPerformanceRepository extends JpaRepository<ParticipantControlPerformance,Integer> {
}
