package com.softwaredev.srimayangvilla.Service;


import com.softwaredev.srimayangvilla.Model.Staff;
import com.softwaredev.srimayangvilla.Repository.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository repo;

    public List<Staff> listAll(){
        return(List<Staff>) repo.findAll();
    }

    public void save(Staff staff) {
        Staff saveStaff= repo.save(staff);


    }
}


