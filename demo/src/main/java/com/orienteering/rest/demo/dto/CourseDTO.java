package com.orienteering.rest.demo.dto;


import java.util.Date;
import java.util.List;

public class CourseDTO {

    private Integer courseId;

    private String courseName;

    private Date courseDate;

    private List<ControlDTO> courseControls;


    public CourseDTO(){
        super();
    }

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

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseControls=" + courseControls +
                '}';
    }
}
