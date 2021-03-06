package org.example.service.interfaces;


import org.example.domain.Activity;
import org.example.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityService {
    Iterable<Activity> showAllActiveUserActivitiesAndArchiveFalse(User user);

    Iterable<Activity> showAllNotActiveUserActivitiesAndArchiveActFalse(User user);

    void addNewActByUser(Activity activity);

    void setTimeActivityById(Long id, Integer time);

    void addNewActByUserWithAddUser(Activity activity, String additionalUser);

    void activateActivityByAdmin(Long id, String additionalUser, String tag);

    Page<Activity> showAllArchiveActivities(Pageable pageable);

    Iterable<Activity> showAllNotActiveActivitiesAndArchiveFalse();

    Iterable<Activity> findActivityByTagAndActiveActFalseAndArchiveActFalse(String filterByTag);

    Iterable<Activity> findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(String filterByUsername);

    Page<Activity> findActivityByTagAndArchiveActTrue(String filterByTag,Pageable pageable);

    Page<Activity> findActivityByUsersAndArchiveActTrue(String filterByUsername,Pageable pageable);
}
