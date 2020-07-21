package com.orienteering.rest.demo.dto;


import com.orienteering.rest.demo.User;

import java.util.Date;
import java.util.List;

public class CourseDTO {

    private Integer courseId;

    private String courseName;

    private Date courseDate;

    private List<ControlDTO> courseControls;

    private UserDTO user;

    public CourseDTO(){ }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }

    public List<ControlDTO> getCourseControls() {
        return courseControls;
    }

    public void setCourseControls(List<ControlDTO> courseControls) {
        this.courseControls = courseControls;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDate=" + courseDate +
                ", courseControls=" + courseControls +
                ", user=" + user +
                '}';
    }
}
