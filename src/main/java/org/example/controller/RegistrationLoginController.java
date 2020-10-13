package org.example.controller;

import org.apache.log4j.Logger;
import org.example.domain.User;
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
import java.util.Map;

@Controller
public class RegistrationLoginController {

    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(RegistrationLoginController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(
            @AuthenticationPrincipal User user
    ) {
        log.info(user);
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

            log.debug("New user: " + user.toString());
            model.addAttribute("emailError", "Юзер  з таким логіном, вже є");
            return "registration";
        }


        return "redirect:login";
    }


}
