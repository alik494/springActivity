package org.example.controller;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.service.interfaces.ActivityService;
import org.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    ActivityService activityService;
    @Autowired
    UserService userService;

    @GetMapping("user" + "{user}")
    public String userForm(
            @PathVariable User user,
            Map<String, Object> model) {
        model.put("user", user);
        model.put("activities", activityService.showAllActiveUserActivities(user));
        return "userCab";
    }

    @PostMapping("/showNotActiveActivities")
    public String showNotActive(
            @AuthenticationPrincipal User user,
            Map<String, Object> model) {
        model.put("user", user);
        model.put("activities", activityService.showAllNotActiveUserActivities(user));
        return "userCab";
    }

    @PostMapping("/addAct")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam String additionalUser,
            Map<String, Object> model) {
        List<User> userList = new ArrayList<>();
        if (additionalUser != null && !additionalUser.isEmpty() && !user.getUsername().equals(additionalUser)) {
            userList.add(userService.findUserByUsername(additionalUser));
        }
        userList.add(user);
        activityService.addNewActByUser(text, tag, userList);
        return "redirect:/user" + user.getId();
    }

    @PostMapping("/sendTime")
    public String sendTime(
            @AuthenticationPrincipal User user,
            @RequestParam Integer time,
            @RequestParam Integer activityId,
            Map<String, Object> model) {
        activityService.setTimeActivityById(activityId, time);
        return "redirect:/user" + user.getId();
    }


}
