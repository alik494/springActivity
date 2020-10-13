package org.example.service;

import org.apache.log4j.Logger;
import org.example.controller.RegistrationLoginController;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.repos.UserRepo;
import org.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        log.debug("New user: " + user.toString());
        User userFromDB = userRepo.findByEmail(user.getEmail());
        if (userFromDB != null) {
            return false;
        }
        user.setActive(true);
        //temporary for admin
        if (user.getUsername().equals("Admin")) {
            user.setRoles(Collections.singleton(Role.ADMIN));
        } else {
            user.setRoles(Collections.singleton(Role.USER));
        }
        userRepo.save(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        userRepo.save(user);
        return true;
    }

    @Override
    public Iterable<User> showAllUsers() {
        return userRepo.findAll();
    }
}
