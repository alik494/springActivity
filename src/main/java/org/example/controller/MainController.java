package org.example.controller;

import org.apache.log4j.Logger;
import org.example.domain.Role;
import org.example.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

@Controller
public class MainController {
    /**
     * Instance of Logger
     */
    private static final Logger logger = Logger.getLogger(MainController.class);

    @GetMapping
    public String greeting(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return "greeting";
    }


    @RequestMapping("/main")
    public String mainAfterLogin(@AuthenticationPrincipal User user) {
        if (user.getRoles().contains(Role.ADMIN)) {
            return "redirect:adminCab";
        }
        return "redirect:user" + user.getId();
    }

}