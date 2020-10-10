package org.example.repos;

import org.example.domain.Activity;
import org.example.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepo extends CrudRepository<Activity,Long> {
        Activity findActivityById(Integer id);
        Iterable <Activity> findActivityByTagAndActiveActFalseAndArchiveActFalse(String tag);

        Page <Activity> findActivityByArchiveActTrue(Pageable pageable);
        Iterable <Activity> findActivityByActiveActFalseAndArchiveActFalse();
        Iterable <Activity> findActivityByUsers(User user);
        Iterable <Activity> findActivityByUsersAndActiveActIsTrueAndArchiveActFalse(User user);
        Iterable <Activity> findActivityByUsersAndActiveActIsFalseAndArchiveActFalse(User user);
        Iterable<Activity> findActivityByUsersAndArchiveActTrue(User user);
        Iterable<Activity> findActivityByTagAndArchiveActTrue(String tag);
}
