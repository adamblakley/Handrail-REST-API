package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.ParticipantControlPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantControlPerformanceRepository extends JpaRepository<ParticipantControlPerformance,Integer> {
}
