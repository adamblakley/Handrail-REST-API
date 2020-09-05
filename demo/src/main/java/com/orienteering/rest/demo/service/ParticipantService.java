package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.Participant;
import com.orienteering.rest.demo.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Service queries repository for Participant objects
 */
@Service
public class ParticipantService {

    /**
     * Default constructor
     */
    public ParticipantService() {
    }
    // Repository to request database queries
    @Autowired
    ParticipantRepository participantRepository;

    /**
     * find all participants
     * @return
     */
    public List<Participant> findAllParticipants(){
        List<Participant> participants = new ArrayList<Participant>();
        participantRepository.findAll().forEach(participant -> participants.add(participant));
        return participants;
    }

    /**
     * save participant
     * @param participant
     */
    public void saveParticipant(Participant participant) { participantRepository.save(participant);
    }

    /**
     * find participant by id
     * @param id
     * @return
     */
    public Participant findParticipant(Integer id){
        return participantRepository.findById(id).get();
    }

    /**
     * delete participant
     * @param id
     */
    public void deleteParticipant(Integer id){
        participantRepository.deleteById(id);
    }
}
