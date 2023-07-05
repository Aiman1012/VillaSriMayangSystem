package com.softwaredev.srimayangvilla.Repository;

import com.softwaredev.srimayangvilla.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends CrudRepository<User, Integer>  {
}
