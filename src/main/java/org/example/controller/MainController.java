package org.example.controller;

import org.example.domain.Activity;
import org.example.domain.User;
import org.example.repos.ActivityRepo;
import org.example.repos.UserRepo;
import org.example.service.ActivityService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    ActivityRepo activityRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    ActivityService activityService;
    @Autowired
    UserService userService;


    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String userName,@RequestParam(required = false) String filter,Map<String, Object> model) {
        Iterable<Activity> activities;
        if (filter != null && !filter.isEmpty()) {
            activities = activityRepo.findActivityByTag(filter);
        } else {
            activities = activityRepo.findAll();
        }
        model.put("activities", activities);
        model.put("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String mainAfterLogin(@AuthenticationPrincipal User user,Map<String, Object> model) {
        activityService.showAllActivities(model);
        return "main";
    }





    @PostMapping("/addAct")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam String additionalUser,
            Map<String, Object> model) {
        List <User>userList=new ArrayList<>();
        if (additionalUser != null && !additionalUser.isEmpty()) {
            userList.add(user);
            userList.add(userService.findUserByUsername(additionalUser));
            activityService.addNewActByUser(text, tag,userList );
            activityService.showAllActivities(model);
            return "redirect:/main";
        }
        userList.add(user);
        activityService.addNewActByUser(text,tag,userList);
        activityService.showAllActivities(model);
        return "redirect:/main";
    }




}