package org.example.service.interfaces;


import org.example.domain.Activity;
import org.example.domain.User;

public interface ActivityService {
    Iterable<Activity> showAllActiveUserActivitiesAndArchiveFalse(User user);

    Iterable<Activity> showAllNotActiveUserActivitiesAndArchiveActFalse(User user);

    void addNewActByUser(Activity activity);

    void setTimeActivityById(Integer id, Integer time);

    void addNewActByUserWithAddUser(Activity activity, String additionalUser);

    void activateActivityByAdmin(Integer id, String additionalUser, String tag);

    Iterable<Activity> showAllArchiveActivities();

    Iterable<Activity> showAllNotActiveActivitiesAndArchiveFalse();

    Iterable<Activity> findActivityByTagAndActiveActFalseAndArchiveActFalse(String filterByTag);

    Iterable<Activity> findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(String filterByUsername);

    Iterable<Activity> findActivityByTagAndArchiveActTrue(String filterByTag);

    Iterable<Activity> findActivityByUsersAndArchiveActTrue(String filterByUsername);
}
