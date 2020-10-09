package org.example.controller;

import org.example.domain.Role;
import org.example.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {
    @GetMapping
    public String greeting() {
        return "greeting";
    }


    @RequestMapping("/main")
    public String mainAfterLogin(@AuthenticationPrincipal User user,Map<String, Object> model) {
        if (user.getRoles().contains(Role.ADMIN)){
            return "redirect:adminCab";
        }
        return "redirect:user"+user.getId();
    }

}