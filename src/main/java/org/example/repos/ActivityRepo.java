package org.example.repos;

import org.example.domain.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepo extends CrudRepository<Activity,Long> {
        List <Activity> findActivityByTag(String tag);
}
