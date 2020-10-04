package org.example.service.interfaces;

import org.example.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;

    public User findUserByUsername(String username);

}
