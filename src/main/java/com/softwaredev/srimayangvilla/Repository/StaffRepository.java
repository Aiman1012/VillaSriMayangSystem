package com.softwaredev.srimayangvilla.Repository;


import com.softwaredev.srimayangvilla.Model.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Integer> {
}


