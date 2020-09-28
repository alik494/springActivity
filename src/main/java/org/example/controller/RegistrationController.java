package org.example.controller;

import org.example.domain.User;
import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.CollectionTable;
import java.util.Collections;
import java.util.Map;

public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {


        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB!=null){

            // model.put("message","user exist");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(null));
        return "redirect:login";
    }

}
