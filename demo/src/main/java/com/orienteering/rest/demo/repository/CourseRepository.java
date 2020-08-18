package com.orienteering.rest.demo.repository;

import com.orienteering.rest.demo.model.Course;
import com.orienteering.rest.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query
    public List<Course> findByCourseUserAndIsActiveTrue(User user);
}
