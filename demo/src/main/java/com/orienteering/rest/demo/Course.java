package com.orienteering.rest.demo;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Course {


    @Id
    @GeneratedValue
    private Integer courseId;

    private String courseName;

    private Date courseDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventCourse", cascade = CascadeType.ALL)
    private List<Event> events;

    @JsonManagedReference
    @OneToMany(mappedBy = "controlCourse", cascade = CascadeType.ALL)
    private List<Control> courseControls;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User courseUser;

    public Course(){
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Control> getCourseControls() {
        return courseControls;
    }

    public void setCourseControls(List<Control> courseControls) {
        this.courseControls = courseControls;
    }

    public User getCourseUser() {
        return courseUser;
    }

    public void setCourseUser(User courseUser) {
        this.courseUser = courseUser;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDate=" + courseDate +
                ", courseControls=" + courseControls +
                ", courseUser=" + courseUser +
                '}';
    }
}
