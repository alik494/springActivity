package org.example.service.interfaces;

import org.example.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;

     User findUserByUsername(String username);
     boolean addUser(User user);
     boolean updateUser(User user);

     Iterable<User> showAllUsers();

}
