package com.orienteering.rest.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class defines directory for upload
 */
@ConfigurationProperties(prefix = "file")
public class ImageUploadProperties {
    // directroy of image files
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
