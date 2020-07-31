package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.Course;
import com.orienteering.rest.demo.StatusResponseEntity;
import com.orienteering.rest.demo.User;
import com.orienteering.rest.demo.dto.CourseDTO;
import com.orienteering.rest.demo.security.models.UserPrincipal;
import com.orienteering.rest.demo.service.CourseService;
import com.orienteering.rest.demo.service.EventService;
import com.orienteering.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseResource {

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/courses")
    public List<CourseDTO> retrieveAllCourses(){

        List<Course> courses = courseService.findAllCourses();
        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }



    @GetMapping("/courses/{id}")
    public CourseDTO retrieveCourses(@PathVariable Integer id){
        Course course = courseService.findCourse(id);
        return convertToDto(course);
    }

    @PostMapping("/courses")
    public Integer createCourses(@Valid @RequestBody Course course){
        courseService.saveCourse(course);
        return course.getCourseId();
    }

    @GetMapping("users/{id}/courses")
    public List<CourseDTO> retrieveAllCoursesByUser(@PathVariable Long id){
        User user = userService.findUser(id);
        List<Course> courses = user.getCourses();
        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("user/courses")
    public List<CourseDTO> retrieveCoursesByUser(@PathVariable Long id){
        User user = userService.findUser(id);
        List<Course> courses = user.getCourses();
        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public StatusResponseEntity<Object> getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal){
            User user = userService.findUser(((UserPrincipal) principal).getId());
            return new StatusResponseEntity(true,"Got User",user);
        } else {
            return new StatusResponseEntity(false, "Failed to get User", false);
        }
    }

    private CourseDTO convertToDto(Course course){
        CourseDTO courseDto = modelMapper.map(course,CourseDTO.class);
        return courseDto;
    }

}
