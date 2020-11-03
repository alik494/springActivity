package org.example.controller;

import org.example.domain.Activity;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.service.interfaces.ActivityService;
import org.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/adminCab")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    ActivityService activityService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.showAllUsers());
        return "userList";
    }


    @GetMapping("activities")
    public String main(@RequestParam(required = false) String filterByUsername,
                       @RequestParam(required = false) String filterByTag,
                       Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Iterable<Activity> activities;
        if (filterByUsername != null && !filterByUsername.isEmpty()) {
            activities = activityService.findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(filterByUsername);
            model.addAttribute("activities", activities);
            return "adminActivity";
        }
        if (filterByTag != null && !filterByTag.isEmpty() && filterByUsername == null) {
            activities = activityService.findActivityByTagAndActiveActFalseAndArchiveActFalse(filterByTag);
        } else {
            activities = activityService.showAllNotActiveActivitiesAndArchiveFalse();
        }
        model.addAttribute("activities", activities);
        return "adminActivity";
    }




    @GetMapping("archiveActivities")
    public String archiveActivities(@RequestParam(required = false) String filterByUsername,
                                    @RequestParam(required = false) String filterByTag,
                                    Model model,
                                    @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Activity> page;

        if (filterByUsername != null && !filterByUsername.isEmpty()) {
            page = activityService.findActivityByUsersAndArchiveActTrue(filterByUsername, pageable);
            model.addAttribute("page", page);
            model.addAttribute("url", "/adminCab/archiveActivities");
            return "adminArchiveActivity";
        }
        if (filterByTag != null && !filterByTag.isEmpty() && filterByUsername == null) {
            page = activityService.findActivityByTagAndArchiveActTrue(filterByTag, pageable);
        } else {
            page = activityService.showAllArchiveActivities(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/adminCab/archiveActivities");
        return "adminArchiveActivity";
    }

    @GetMapping("archiveActivities/sortByText")
    public String sortByText(@RequestParam(required = false) String filterByUsername,
                             @RequestParam(required = false) String filterByTag,
                             Model model,
                             @PageableDefault(sort = {"text"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Activity> page;

        if (filterByUsername != null && !filterByUsername.isEmpty()) {
            page = activityService.findActivityByUsersAndArchiveActTrue(filterByUsername, pageable);
            model.addAttribute("page", page);
            model.addAttribute("url", "/adminCab/archiveActivities/sortByText");
            return "adminArchiveActivity";
        }
        if (filterByTag != null && !filterByTag.isEmpty() && filterByUsername == null) {
            page = activityService.findActivityByTagAndArchiveActTrue(filterByTag, pageable);
        } else {
            page = activityService.showAllArchiveActivities(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/adminCab/archiveActivities/sortByText");
        return "adminArchiveActivity";
    }

    @GetMapping("archiveActivities/sortByTag")
    public String sortByTag(@RequestParam(required = false) String filterByUsername,
                             @RequestParam(required = false) String filterByTag,
                             Model model,
                             @PageableDefault(sort = {"tag"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Activity> page;

        if (filterByUsername != null && !filterByUsername.isEmpty()) {
            page = activityService.findActivityByUsersAndArchiveActTrue(filterByUsername, pageable);
            model.addAttribute("page", page);
            model.addAttribute("url", "/adminCab/archiveActivities/sortByTag");
            return "adminArchiveActivity";
        }
        if (filterByTag != null && !filterByTag.isEmpty() && filterByUsername == null) {
            page = activityService.findActivityByTagAndArchiveActTrue(filterByTag, pageable);
        } else {
            page = activityService.showAllArchiveActivities(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/adminCab/archiveActivities/sortByTag");
        return "adminArchiveActivity";
    }

    @PostMapping("activateActivity")
    public String activateActivity(@RequestParam(required = false) String editTagAct,
                                   @RequestParam(required = false) String additionalUsername,
                                   @RequestParam Long activityId
    ) {
        activityService.activateActivityByAdmin(activityId, additionalUsername, editTagAct);
        return "redirect:/adminCab/activities";
    }

    @GetMapping("user" + "{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        user.setActive(Boolean.parseBoolean(form.get("inputState")));
        userService.updateUser(user);
        return "redirect:/adminCab";
    }


}
