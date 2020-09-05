package com.orienteering.rest.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Course object represents a map course for an orienteering event
 */
@Entity
public class Course {
    // course id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    // course name
    private String courseName;
    //course note
    private String courseNote;
    // course creation date
    private Date courseDate;
    // events associate to course
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventCourse", cascade = CascadeType.ALL)
    private List<Event> events;
    // course controls
    @JsonManagedReference
    @OneToMany(mappedBy = "controlCourse", cascade = CascadeType.ALL)
    private List<Control> courseControls;
    // course organiser
    @ManyToOne
    @JoinColumn(name="user_id")
    private User courseUser;
    // is course deleted or active
    private boolean isActive;

    /**
     * Default constructor
     */
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

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseNote='" + courseNote + '\'' +
                ", courseDate=" + courseDate +
                ", courseControls=" + courseControls +
                ", courseUser=" + courseUser +
                ", isActive=" + isActive +
                '}';
    }


}
