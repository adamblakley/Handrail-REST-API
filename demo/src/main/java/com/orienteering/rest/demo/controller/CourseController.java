package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.dto.CourseDTO;
import com.orienteering.rest.demo.model.*;
import com.orienteering.rest.demo.security.models.UserPrincipal;
import com.orienteering.rest.demo.service.CourseService;
import com.orienteering.rest.demo.service.ImageUploadService;
import com.orienteering.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller class for Course objects
 */
@RestController
public class CourseController {

    // service class to request objects from the repository
    @Autowired
    CourseService courseService;
    // service class to request objects from the repository
    @Autowired
    UserService userService;
    // service class to request objects from the repository
    @Autowired
    ImageUploadService imageUploadService;
    // model mapper, maps resources to DTOs
    @Autowired
    ModelMapper modelMapper;

    /**
     * List all courses
     * @return
     */
    @GetMapping("/courses")
    public List<CourseDTO> retrieveAllCourses(){

        List<Course> courses = courseService.findAllCourses();
        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * List course by id
     * @param id
     * @return
     */
    @GetMapping("/courses/{id}")
    public ResponseEntity<StatusResponseEntity<CourseDTO>> retrieveCourseById(@PathVariable Integer id){
        Course course = courseService.findCourse(id);
        CourseDTO courseDTO = convertToDto(course);
        return new ResponseEntity( new StatusResponseEntity(true, "Courses Found",courseDTO), HttpStatus.OK);

    }

    /**
     * Get courses by user
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/courses")
    public ResponseEntity<StatusResponseEntity<List<CourseDTO>>> findCoursesByUser(@PathVariable Long id){
        User user = userService.findUser(id);
        List<Course> courses = courseService.findCoursesByUser(user);
        return new ResponseEntity( new StatusResponseEntity(true, "Course Found",courses.stream().map(this::convertToDto).collect(Collectors.toList())), HttpStatus.OK);
    }

    /**
     * Create a course by user id
     * @param id
     * @param courseDto
     * @param files
     * @return
     */
    @PostMapping("/users/{id}/courses/upload")
    @ResponseBody
    public ResponseEntity<StatusResponseEntity<Course>> createCourses(@PathVariable Long id,@Valid @RequestPart("course") CourseDTO courseDto, @RequestPart("file") MultipartFile[] files){

        Course course = convertToEntity(courseDto);
        course.setActive(true);
        // loop through each control, nested loop through each file. If a file name matches a control name, save the file and associate
        for(Control control :course.getCourseControls()){

            control.setControlCourse(course);

            for (int i=0;i<files.length;i++){
                System.out.println(files[i].getOriginalFilename());
                if (files[i].getOriginalFilename().equals(control.getControlName())){
                    // save the file and associate the filepath returned to the photopath of a ControlPhotograph object, associate this to the Control object
                    MultipartFile file = files[i];
                    ImageUploadResponse imageUploadResponse = imageUploadService.uploadFileRequest(file);
                    ControlPhotograph photograph = new ControlPhotograph();
                    photograph.setPhotoPath(imageUploadResponse.getFilepath());
                    photograph.setPhotoName(file.getOriginalFilename());
                    photograph.setActive(true);
                    photograph.setEntity(control);
                    control.setControlPhotographs(new ArrayList<ControlPhotograph>());
                    control.getControlPhotographs().add(photograph);
                    break;
                }
            }
        }
        // save the Course with an associated user and timestamp of creation, convert to DTO for return to the client
        User user = userService.findUser(id);
        course.setCourseUser(user);
        course.setCourseDate(Calendar.getInstance().getTime());
        Course courseSaved = courseService.saveCourse(course);
        CourseDTO courseDtoSaved = convertToDto(courseSaved);
        return new ResponseEntity( new StatusResponseEntity(true, "Course creation success",courseDtoSaved), HttpStatus.OK);
    }

    public ImageUploadResponse uploadControlPhotograph(MultipartFile file){
        ImageUploadResponse imageUploadResponse = imageUploadService.uploadFileRequest(file);
        return imageUploadResponse;
    }

    /**
     * Get all courses by user
     * @param id
     * @return
     */
    @GetMapping("user/courses")
    public List<CourseDTO> retrieveCoursesByUser(@PathVariable Long id){
        User user = userService.findUser(id);
        List<Course> courses = user.getCourses();
        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Get user from securitycontextholder principle
     * @return
     */
    public StatusResponseEntity<Object> getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal){
            User user = userService.findUser(((UserPrincipal) principal).getId());
            return new StatusResponseEntity(true,"Got User",user);
        } else {
            return new StatusResponseEntity(false, "Failed to get User", false);
        }
    }

    /**
     * Delete a course
     * @param id
     * @return
     */
    @PutMapping("courses/{id}/delete")
    public ResponseEntity<StatusResponseEntity<Boolean>> deleteCourse(@PathVariable int id){
        // find course by id
        Course course = courseService.findCourse(id);
        // if course found and is already deleted, return conflict else set to inactive and save
        if (course!=null){
            if (!course.isActive()){
                return new ResponseEntity( new StatusResponseEntity(false, "Course Already Removed",false), HttpStatus.CONFLICT);
            }
            course.setActive(false);
            courseService.saveCourse(course);
            return new ResponseEntity( new StatusResponseEntity(true, "Course Successfully Deleted",true), HttpStatus.OK);
        } else {
            return new ResponseEntity( new StatusResponseEntity(false, "Course Removal Unsuccessful",false), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Convert Course to CourseDTO
     * @param course
     * @return
     */
    private CourseDTO convertToDto(Course course){
        CourseDTO courseDto = modelMapper.map(course,CourseDTO.class);
        return courseDto;
    }

    /**
     * Convert CourseDTO to Course
     * @param courseDTO
     * @return
     */
    private Course convertToEntity(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Course.class);
    }

}
