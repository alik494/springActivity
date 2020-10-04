package org.example.service;

import org.example.domain.Activity;
import org.example.domain.User;
import org.example.repos.ActivityRepo;
import org.example.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityRepo activityRepo;

    public void showAllActivities(Map<String, Object> model){
        Iterable<Activity> all = activityRepo.findAll();
        model.put("activities", all);
    }

    public void addNewActByUser(String text, String tag, List<User> userList) {
        Activity activity = new Activity(text, tag, userList);
        activityRepo.save(activity);
    }
}
