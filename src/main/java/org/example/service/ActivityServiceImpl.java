package org.example.service;

import org.example.domain.Activity;
import org.example.domain.User;
import org.example.repos.ActivityRepo;
import org.example.repos.UserRepo;
import org.example.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityRepo activityRepo;
    @Autowired
    UserRepo userRepo;



    @Override
    public Iterable<Activity> showAllArchiveActivities() {
        return activityRepo.findActivityByArchiveActTrue();
    }

    @Override
    public Iterable<Activity> showAllNotActiveActivitiesAndArchiveFalse() {
        return activityRepo.findActivityByActiveActFalseAndArchiveActFalse();
    }

    @Override
    public Iterable<Activity> showAllActiveUserActivitiesAndArchiveFalse(User user) {
        return activityRepo.findActivityByUsersAndActiveActIsTrueAndArchiveActFalse(user);
    }

    @Override
    public Iterable<Activity> showAllNotActiveUserActivitiesAndArchiveActFalse(User user) {
        return activityRepo.findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(user);
    }

    @Override
    public void setTimeActivityById(Integer id,Integer time) {
        Activity activity=activityRepo.findActivityById(id);
        activity.setTime(time);
        activity.setActiveAct(false);
        activity.setArchiveAct(true);
        activityRepo.save(activity);
    }


    public void addNewActByUser(Activity activity) {
        activityRepo.save(activity);
    }



    @Override
    public void activateActivityByAdmin(Integer id,String additionalUser,String tag) {
        Activity activity=activityRepo.findActivityById(id);
        User addedUser=userRepo.findByUsername(additionalUser);

        if (additionalUser != null && !additionalUser.isEmpty()&&!activity.getUsers().contains(addedUser)){
            List<User> users=activity.getUsers();
            users.add(addedUser);
            activity.setUsers(users);
        }
        if (tag != null && !tag.isEmpty()){

            activity.setTag(tag);
        }
        activity.setActiveAct(true);
        activityRepo.save(activity);
    }

    @Override
    public Iterable<Activity> findActivityByTag(String filterByTag) {
        return activityRepo.findActivityByTag(filterByTag);
    }

    @Transactional
    @Override
    public void addNewActByUserWithAddUser(Activity activity, String additionalUser) {
        List<User> users=activity.getUsers();
        users.add(userRepo.findByUsername(additionalUser));
        activity.setUsers(users);
        activityRepo.save(activity);
    }
}
