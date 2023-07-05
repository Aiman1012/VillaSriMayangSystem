package com.softwaredev.srimayangvilla.Service;

import com.softwaredev.srimayangvilla.Model.User;
import com.softwaredev.srimayangvilla.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public static void deletePackagebyid(String id) {
    }

    public List<User> listAll(){
        return(List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);


    }

    public void updateUser(User user) {
    }
}
