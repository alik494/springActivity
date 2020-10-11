package org.example.controller;

import freemarker.template.utility.StringUtil;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.repos.UserRepo;
import org.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationLoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(
            @AuthenticationPrincipal User user

    ) {
        if (user != null) {
            return "greeting";
        }

        return "login";
    }

    @GetMapping("/registration")
    public String registration(
    ) {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConformation,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {
        boolean isConformationEmpty = StringUtils.isEmpty(passwordConformation);

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConformation)) {
            model.addAttribute("passwordError", "Password are different");
            return "registration";
        }
        if (isConformationEmpty) {
            model.addAttribute("password2Error", "Password conformation cannot be empty");
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "Юзер вже є з таким логіном!");
            return "registration";
        }


        return "redirect:login";
    }


}
