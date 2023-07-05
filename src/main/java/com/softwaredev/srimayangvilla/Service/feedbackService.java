package com.softwaredev.srimayangvilla.Service;


import com.softwaredev.srimayangvilla.Model.feedback;
import com.softwaredev.srimayangvilla.Repository.feedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class feedbackService {
    @Autowired
    private feedbackRepository repo;

    public List<feedback> listAll(){
        return(List<feedback>) repo.findAll();
    }

    public void save(feedback feedback) {
        feedback savefeedback= repo.save(feedback);


    }
}

