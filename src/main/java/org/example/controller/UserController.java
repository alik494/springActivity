package org.example.controller;

import org.example.domain.Activity;
import org.example.domain.User;
import org.example.service.interfaces.ActivityService;
import org.example.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    ActivityService activityService;
    @Autowired
    UserService userService;

    @RequestMapping("user" + "{user}")
    public String userForm(
            @PathVariable User user,
            Model model) {
        model.addAttribute("user", user);
        model.addAttribute("activities", activityService.showAllActiveUserActivitiesAndArchiveFalse(user));
        return "userCab";
    }

    @GetMapping("/showNotActiveActivities")
    public String showNotActive(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("user", user);
        model.addAttribute("activities", activityService.showAllNotActiveUserActivitiesAndArchiveActFalse(user));
        return "userCabShowNotAct";
    }


    @PostMapping("/addAct")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String additionalUser,
            @Valid Activity activity,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("activity", activity);
            model.addAttribute("activities", activityService.showAllActiveUserActivitiesAndArchiveFalse(user));
            return "userCab";
        } else {
            List<User> userList = new ArrayList<>();
            userList.add(user);
            activity.setUsers(userList);
            if (additionalUser != null && !additionalUser.isEmpty() && !user.getUsername().equals(additionalUser)) {
                activityService.addNewActByUserWithAddUser(activity, additionalUser);
                return "redirect:main";
            }
            activityService.addNewActByUser(activity);
        }
        return "redirect:main";
    }


    @PostMapping("/sendTime")
    public String sendTime(
            @AuthenticationPrincipal User user,
            @RequestParam Integer activityId,
            @RequestParam String time,
            Model model) {
        int timeInt;
        if (StringUtils.isEmpty(time)) {
            model.addAttribute("timeError", "Time connot be empty");
            model.addAttribute("activities", activityService.showAllActiveUserActivitiesAndArchiveFalse(user));
            return "userCab";
        }
        try {
            timeInt = Integer.parseInt(time);
        } catch (NumberFormatException ex) {
            model.addAttribute("timeError", "Parse number");
            model.addAttribute("activities", activityService.showAllActiveUserActivitiesAndArchiveFalse(user));
            return "userCab";
        }
        if (timeInt < 0) {
            model.addAttribute("timeError", "number must be more then 0");
            model.addAttribute("activities", activityService.showAllActiveUserActivitiesAndArchiveFalse(user));
            return "userCab";
        }
        activityService.setTimeActivityById(activityId, timeInt);
        return "redirect:/user" + user.getId();
    }


}
