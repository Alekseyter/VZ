package com.example.repos;

import com.example.domein.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findBySurname(String surname);

}