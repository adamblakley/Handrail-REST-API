package com.orienteering.rest.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Super class represents a photograph
 * @param <T>
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Photograph<T> {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;
    // Name of photo
    private String photoName;
    // Photo filepath
    private String photoPath;
    // is photograph the active photograph
    private Boolean isActive;
    // related entity, delcared in child
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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
