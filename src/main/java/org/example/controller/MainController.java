package org.example.controller;

import org.example.domain.Activity;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.repos.ActivityRepo;
import org.example.repos.UserRepo;
import org.example.service.ActivityServiceImpl;
import org.example.service.UserServiceImpl;
import org.example.service.interfaces.ActivityService;
import org.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @GetMapping
    public String greeting() {
        return "greeting";
    }

//    @GetMapping("/main")
//    public String main(@AuthenticationPrincipal User user,
//                       @RequestParam(required = false) String userName,
//                       @RequestParam(required = false) String filter,
//                       Map<String, Object> model) {
////        if (user.getRoles().contains(Role.ADMIN)){
////            return "redirect:user";
////        }
//
//        Iterable<Activity> activities;
//        if (filter != null && !filter.isEmpty()) {
//            activities = activityRepo.findActivityByTag(filter);
//        } else {
//            activities = activityRepo.findAll();
//        }
//        model.put("activities", activities);
//        model.put("filter", filter);
//        return "main";
//    }

//    @GetMapping("/main")
    @RequestMapping("/main")
    public String mainAfterLogin(@AuthenticationPrincipal User user,Map<String, Object> model) {
        if (user.getRoles().contains(Role.ADMIN)){
            return "redirect:adminCab";
        }
        //activityService.showAllActivities(model);
        return "redirect:user"+user.getId();
    }










}