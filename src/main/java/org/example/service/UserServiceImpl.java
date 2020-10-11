package org.example.service;

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

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
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
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setActive(true);
        //temporary for admin
        if (user.getUsername().equals("admin")) {
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
