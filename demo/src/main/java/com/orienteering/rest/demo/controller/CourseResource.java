package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.Course;
import com.orienteering.rest.demo.dto.CourseDTO;
import com.orienteering.rest.demo.service.CourseService;
import com.orienteering.rest.demo.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseResource {

    @Autowired
    CourseService courseService;

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

    private CourseDTO convertToDto(Course course){
        CourseDTO courseDto = modelMapper.map(course,CourseDTO.class);
        return courseDto;
    }

}
