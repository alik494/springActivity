package org.example.controller;

import org.example.domain.Activity;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationLoginController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ) {
        if (user!=null){
            return "greeting";
        }
        model.put("messages", "Please log in");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Map<String, Object> model
    ) {
        model.put("messages", "Add new user");
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("messages", "Юзер вже є з таким логіном!");
            return "registration";
        }
        model.put("messages", "And now, please log in");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
//        return "redirect:login";
        return "login";
    }


}
