package org.example.controller;

import org.example.domain.Activity;
import org.example.domain.User;
import org.example.repos.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    ActivityRepo activityRepo;


    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Activity> all = activityRepo.findAll();
        model.put("activities", all);
        return "main";
    }

    @PostMapping("/main")
    public String mainAfterLogin(Map<String, Object> model){
        Iterable<Activity> all = activityRepo.findAll();
        model.put("activities", all);
        return "main";
    }

    @PostMapping("/addAct")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {
        Activity activity = new Activity(text, tag,user);
        activityRepo.save(activity);
        Iterable<Activity> all = activityRepo.findAll();
        model.put("activities", all);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Activity> activities;
        if (filter != null && !filter.isEmpty()) {
            activities = activityRepo.findActivityByTag(filter);
        } else {
            activities = activityRepo.findAll();
        }
        model.put("activities", activities);
        return "main";
    }


}