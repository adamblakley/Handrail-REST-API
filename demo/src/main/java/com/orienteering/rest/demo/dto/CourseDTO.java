package com.orienteering.rest.demo.dto;


import java.util.Date;
import java.util.List;
/**
 * DTO for Course Object
 */
public class CourseDTO {
    // course id
    private Integer courseId;
    // course name
    private String courseName;
    //course note
    private String courseNote;
    // course creation date
    private Date courseDate;
    // course controls
    private List<ControlDTO> courseControls;
    // course creator
    private UserDTO user;

    /**
     * Default constructor
     */
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

    public String getCourseNote() { return courseNote; }

    public void setCourseNote(String courseNote) { this.courseNote = courseNote; }

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
                ", courseNote='" + courseNote + '\'' +
                ", courseDate=" + courseDate +
                ", courseControls=" + courseControls +
                ", user=" + user +
                '}';
    }
}
