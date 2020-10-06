package org.example.service.interfaces;



import org.example.domain.Activity;
import org.example.domain.User;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    public Iterable<Activity> showAllActivities();
    public Iterable<Activity>  showAllUserActivities(User user);
    public Iterable<Activity>  showAllActiveUserActivities(User user);
    public Iterable<Activity>  showAllNotActiveUserActivities(User user);
    public void  setTimeActivityById(Integer id,Integer time);
    public void addNewActByUser(String text, String tag, List<User> userList) ;
}
