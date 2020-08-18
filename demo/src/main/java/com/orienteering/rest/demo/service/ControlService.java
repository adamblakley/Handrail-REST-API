package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.Control;
import com.orienteering.rest.demo.repository.ControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ControlService {

    public ControlService() {
    }

    @Autowired
    ControlRepository controlRepository;

    public List<Control> findAllControls(){
        List<Control> controls = new ArrayList<Control>();
        controlRepository.findAll().forEach(control -> controls.add(control));
        return controls;
    }

    public void saveControl(Control control) { controlRepository.save(control);
    }

    public Control findControl(Integer id){
        return controlRepository.findById(id).get();
    }

    public void deleteControl(Integer id){
        controlRepository.deleteById(id);
    }

    /**
     *     public controlDTO saveControl(Control control) { controlRepository.save(control);
     *         Control control = controlRepository.save(control);
     *     }
     *
     *     public controlDTO (Control){
     *
     *     }
     */

}
