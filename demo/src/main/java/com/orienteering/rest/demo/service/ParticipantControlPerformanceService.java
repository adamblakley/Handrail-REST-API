package com.orienteering.rest.demo.service;


import com.orienteering.rest.demo.ParticipantControlPerformance;

import com.orienteering.rest.demo.repository.ParticipantControlPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ParticipantControlPerformanceService {

    public ParticipantControlPerformanceService() {
    }

    @Autowired
    ParticipantControlPerformanceRepository participantControlPerformanceRepository;

    public List<ParticipantControlPerformance> findAllPcps(){
        List<ParticipantControlPerformance> participantControlPerformances = new ArrayList<ParticipantControlPerformance>();
        participantControlPerformanceRepository.findAll().forEach(participantControlPerformance -> participantControlPerformances.add(participantControlPerformance));
        return participantControlPerformances;
    }

    public void savePcp(ParticipantControlPerformance participantControlPerformance) { participantControlPerformanceRepository.save(participantControlPerformance);
    }

    public ParticipantControlPerformance findPcp(Integer id){
        return participantControlPerformanceRepository.findById(id).get();
    }

    public void deletePcp(Integer id){
        participantControlPerformanceRepository.deleteById(id);
    }
}
