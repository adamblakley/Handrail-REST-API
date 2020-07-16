package com.orienteering.rest.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ControlPhotograph {

    @Id
    @GeneratedValue
    private Integer controlPhotoId;
    private String controlPhotoName;
    private String controlPhotoPath;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    private Control control;

    public ControlPhotograph() {
        super();
    }

    public Integer getControlPhotoId() {
        return controlPhotoId;
    }

    public void setControlPhotoId(Integer controlPhotoId) {
        this.controlPhotoId = controlPhotoId;
    }

    public String getControlPhotoName() {
        return controlPhotoName;
    }

    public void setControlPhotoName(String controlPhotoName) {
        this.controlPhotoName = controlPhotoName;
    }

    public String getControlPhotoPath() {
        return controlPhotoPath;
    }

    public void setControlPhotoPath(String controlPhotoPath) {
        this.controlPhotoPath = controlPhotoPath;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    @Override
    public String toString() {
        return "ControlPhotograph{" +
                "controlPhotoId=" + controlPhotoId +
                ", controlPhotoName='" + controlPhotoName + '\'' +
                ", controlPhotoPath='" + controlPhotoPath + '\'' +
                ", control=" + control +
                '}';
    }
}
