package org.example.repos;

import org.example.domain.Activity;
import org.example.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepo extends CrudRepository<Activity,Long> {
        Activity findActivityById(Integer id);
        Iterable <Activity> findActivityByTagAndActiveActFalseAndArchiveActFalse(String tag);

        Iterable <Activity> findActivityByArchiveActTrue();
        Iterable <Activity> findActivityByActiveActFalseAndArchiveActFalse();
        Iterable <Activity> findActivityByUsers(User user);
        Iterable <Activity> findActivityByUsersAndActiveActIsTrueAndArchiveActFalse(User user);
        Iterable <Activity> findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(User user);
        Iterable<Activity> findActivityByUsersAndArchiveActTrue(User user);
        Iterable<Activity> findActivityByTagAndArchiveActTrue(String tag);
}
