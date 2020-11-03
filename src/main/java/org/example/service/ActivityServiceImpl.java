package org.example.service;

import org.example.domain.Activity;
import org.example.domain.User;
import org.example.repos.ActivityRepo;
import org.example.repos.UserRepo;
import org.example.service.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityRepo activityRepo;
    @Autowired
    UserRepo userRepo;


    @Override
    public Page<Activity> showAllArchiveActivities(Pageable pageable) {
        return activityRepo.findActivityByArchiveActTrue(pageable);
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
    @Transactional
    @Override
    public void setTimeActivityById(Long id, Integer time) {
        Activity activity = activityRepo.findActivityById(id);
        activity.setTime(time);
        activity.setActiveAct(false);
        activity.setArchiveAct(true);
        activityRepo.save(activity);
    }


    public void addNewActByUser(Activity activity) {
        activityRepo.save(activity);
    }


    @Transactional
    @Override
    public void activateActivityByAdmin(Long id, String additionalUser, String tag) {
        Activity activity = activityRepo.findActivityById(id);
        User addedUser = null;
        if (!StringUtils.isEmpty(additionalUser)){
             addedUser = userRepo.findByUsername(additionalUser);
        }

        if (additionalUser != null && !additionalUser.isEmpty() && !activity.getUsers().contains(addedUser)) {
            List<User> users = activity.getUsers();
            users.add(addedUser);
            activity.setUsers(users);
        }
        if (tag != null && !tag.isEmpty()) {
            activity.setTag(tag);
        }
        activity.setActiveAct(true);
        activity.setTime(1);
        activityRepo.save(activity);
    }


    @Transactional
    @Override
    public void addNewActByUserWithAddUser(Activity activity, String additionalUser) {
        List<User> users = activity.getUsers();
        users.add(userRepo.findByUsername(additionalUser));
        activity.setUsers(users);
        activityRepo.save(activity);
    }
    @Transactional
    @Override
    public Iterable<Activity> findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(String filterByUsername) {
        User user = userRepo.findByUsername(filterByUsername);
        return activityRepo.findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(user);
    }


    @Override
    public Iterable<Activity> findActivityByTagAndActiveActFalseAndArchiveActFalse(String filterByTag) {
        return activityRepo.findActivityByTagAndActiveActFalseAndArchiveActFalse(filterByTag);
    }
    @Transactional
    @Override
    public Page<Activity> findActivityByUsersAndArchiveActTrue(String filterByUsername,Pageable pageable) {
        User user = userRepo.findByUsername(filterByUsername);
        return activityRepo.findActivityByUsersAndArchiveActTrue(user,pageable);

    }

    @Override
    public Page<Activity> findActivityByTagAndArchiveActTrue(String filterByTag,Pageable pageable) {
        return activityRepo.findActivityByTagAndArchiveActTrue(filterByTag,pageable);

    }
}
