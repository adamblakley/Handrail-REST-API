package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.Participant;
import com.orienteering.rest.demo.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {

    public ParticipantService() {
    }

    @Autowired
    ParticipantRepository participantRepository;

    public List<Participant> findAllParticipants(){
        List<Participant> participants = new ArrayList<Participant>();
        participantRepository.findAll().forEach(participant -> participants.add(participant));
        return participants;
    }

    public void saveParticipant(Participant participant) { participantRepository.save(participant);
    }

    public Participant findParticipant(Integer id){
        return participantRepository.findById(id).get();
    }

    public void deleteParticipant(Integer id){
        participantRepository.deleteById(id);
    }
}
