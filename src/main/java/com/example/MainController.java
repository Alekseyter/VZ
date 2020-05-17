package com.example;

import com.example.domein.User;
import com.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;

    //Registration
    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(@RequestParam String name, @RequestParam String surname) {
        User user = new User(name, surname);
        userRepo.save(user);
        return "reg";
    }

    //Avtorization
    @GetMapping("/")
    public String avtirization() {
        return "vhod";
    }

    //Main
    @GetMapping("/main")
    public String main(Map<String,Object> model) {
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<User> users;

        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findBySurname(filter);
        } else users = userRepo.findAll();

        model.put("users", users);
        return "main";
    }

}
