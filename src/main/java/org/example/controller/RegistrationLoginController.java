package org.example.controller;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.repos.UserRepo;
import org.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationLoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ) {
        if (user!=null){
            return "greeting";
        }
        if (user!=null&&!user.isActive()){
            model.put("messages", "аккаунт було  деактивовано");
            return "login";
        }
        model.put("messages", "Please log in");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Map<String, Object> model
    ) {
        model.put("messages", "Add new user");
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (user.getPassword()!=null&&!user.getPassword().equals(user.getPassword2())){
            model.addAttribute("passwordError","Password are different");
            return "registration";
        }
        if (bindingResult.hasErrors()){
            Map<String,String> errors=ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "Юзер вже є з таким логіном!");
            return "registration";
        }


        return "redirect:login";
    }


}
