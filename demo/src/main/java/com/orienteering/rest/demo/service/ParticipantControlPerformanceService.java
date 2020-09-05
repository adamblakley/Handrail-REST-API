package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.ParticipantControlPerformance;

import com.orienteering.rest.demo.repository.ParticipantControlPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Service queries repository for ParticipantControlPerformance objects
 */
@Service
public class ParticipantControlPerformanceService {

    /**
     * Default constructor
     */
    public ParticipantControlPerformanceService() {
    }
    // Repository to request database queries
    @Autowired
    ParticipantControlPerformanceRepository participantControlPerformanceRepository;

    /**
     * find all pcps
     * @return
     */
    public List<ParticipantControlPerformance> findAllPcps(){
        List<ParticipantControlPerformance> participantControlPerformances = new ArrayList<ParticipantControlPerformance>();
        participantControlPerformanceRepository.findAll().forEach(participantControlPerformance -> participantControlPerformances.add(participantControlPerformance));
        return participantControlPerformances;
    }

    /**
     * Save pcp
     * @param participantControlPerformance
     */
    public void savePcp(ParticipantControlPerformance participantControlPerformance) { participantControlPerformanceRepository.save(participantControlPerformance);
    }

    /**
     * find pcp by id
     * @param id
     * @return
     */
    public ParticipantControlPerformance findPcp(Integer id){
        return participantControlPerformanceRepository.findById(id).get();
    }

    /**
     * delete pcp
     * @param id
     */
    public void deletePcp(Integer id){
        participantControlPerformanceRepository.deleteById(id);
    }
}
