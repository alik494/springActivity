package org.example.controller;

import org.example.domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.repos.ActivityRepo;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    final
    ActivityRepo activityRepo;

    @Autowired
    public GreetingController(ActivityRepo activityRepo) {
        this.activityRepo = activityRepo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Activity> all = activityRepo.findAll();
        model.put("activities", all);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Activity activity = new Activity(text, tag);
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