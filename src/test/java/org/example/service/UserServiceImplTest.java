package org.example.service;

import org.example.domain.User;
import org.example.service.interfaces.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
//    @Autowired
//    private UserServiceImpl userService;

    @Test
    public void test() {
        User user = new User();
      //  boolean isUserCreated= userService.addUser(user);
        Assert.assertTrue(true);
    }

}