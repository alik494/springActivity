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

    public Iterable<Activity> showAllActivities(){
        return activityRepo.findAll();
    }

    @Override
    public Iterable <Activity> showAllUserActivities(User user) {
        return activityRepo.findActivityByUsers(user);
    }

    @Override
    public Iterable<Activity> showAllActiveUserActivities(User user) {
        return activityRepo.findActivityByUsersAndActiveActIsTrue(user);
    }

    @Override
    public Iterable<Activity> showAllNotActiveUserActivities(User user) {
        return activityRepo.findActivityByUsersAndActiveActIsFalse(user);
    }

    @Override
    public void setTimeActivityById(Integer id,Integer time) {
        Activity activity=activityRepo.findActivityById(id);
        activity.setTime(time);
        activity.setActiveAct(false);
        activityRepo.save(activity);
    }


    public void addNewActByUser(String text, String tag, List<User> userList) {
        Activity activity=new Activity(text, tag, userList);
        activity.setTime(0);
        activityRepo.save(activity);

    }
}
