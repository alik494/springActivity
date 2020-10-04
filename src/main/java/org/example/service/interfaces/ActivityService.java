package org.example.service.interfaces;



import org.example.domain.Activity;
import org.example.domain.User;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    public void showAllActivities(Map<String, Object> model);
    public void addNewActByUser(String text, String tag, List<User> userList) ;
}
