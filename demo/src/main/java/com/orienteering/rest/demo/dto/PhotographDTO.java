package com.orienteering.rest.demo.dto;

/**
 * Photograph DTO for Photograph Super Class
 */
public class PhotographDTO {

    // Id
    private Long photoId;
    // Name of photo
    private String photoName;
    // Photo filepath
    private String photoPath;

    /**
     * Default constructor
     */
    public PhotographDTO() {
    }

    /**
     * Constructor with args
     * @param photoId
     * @param photoName
     * @param photoPath
     */
    public PhotographDTO(Long photoId, String photoName, String photoPath) {
        this.photoId = photoId;
        this.photoName = photoName;
        this.photoPath = photoPath;
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

    /**
     * To String
     * @return
     */
    @Override
    public String toString() {
        return "PhotographDTO{" +
                "photoId=" + photoId +
                ", photoName='" + photoName + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
