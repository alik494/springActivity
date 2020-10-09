package org.example.repos;

import org.example.domain.Activity;
import org.example.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepo extends CrudRepository<Activity,Long> {
        List <Activity> findActivityByTag(String tag);
        List <Activity> findActivityByArchiveActTrue();
        List <Activity> findActivityByActiveActFalseAndArchiveActFalse();
        List <Activity> findActivityByUsers(User user);
        List <Activity> findActivityByUsersAndActiveActIsTrueAndArchiveActFalse(User user);
        List <Activity> findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(User user);
        Activity findActivityById(Integer id);

}
