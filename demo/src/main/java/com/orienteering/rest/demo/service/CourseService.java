package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.Course;
import com.orienteering.rest.demo.Event;
import com.orienteering.rest.demo.repository.CourseRepository;
import com.orienteering.rest.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    public CourseService() {
    }

    @Autowired
    CourseRepository courseRepository;
    @Transactional(readOnly = true)
    public List<Course> findAllCourses(){
        List<Course> courses = new ArrayList<Course>();
        courseRepository.findAll().forEach(course -> courses.add(course));
        return courses;
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }
    @Transactional(readOnly = true)
    public Course findCourse(Integer id){
        return courseRepository.findById(id).get();
    }

    //public Course findCoursebyEventID(Integer eventId) {return courseRepository}

    public void deleteCourse(Integer id){
        courseRepository.deleteById(id);
    }
}
