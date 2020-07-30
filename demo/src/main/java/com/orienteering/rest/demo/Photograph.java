package com.orienteering.rest.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Photograph<T> {

    @Id
    @GeneratedValue
    private Long photoId;
    private String photoName;
    private String photoPath;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referenceId")
    protected T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "Photograph{" +
                "photoId=" + photoId +
                ", photoName='" + photoName + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
