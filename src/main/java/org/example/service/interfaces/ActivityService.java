package org.example.service.interfaces;


import org.example.domain.Activity;
import org.example.domain.User;

import java.util.List;

public interface ActivityService {
    public Iterable<Activity> showAllArchiveActivities();
    public Iterable<Activity> showAllNotActiveActivitiesAndArchiveFalse();
    public Iterable<Activity> showAllActiveUserActivitiesAndArchiveFalse(User user);
    public Iterable<Activity> showAllNotActiveUserActivitiesAndArchiveActFalse(User user);
    public void  setTimeActivityById(Integer id,Integer time);
    public void addNewActByUser(Activity activity) ;
    public void activateActivityByAdmin(Integer id,String additionalUser,String tag)  ;

    Iterable<Activity> findActivityByTag(String filterByTag);

    void addNewActByUserWithAddUser(Activity activity, String additionalUser);
}
