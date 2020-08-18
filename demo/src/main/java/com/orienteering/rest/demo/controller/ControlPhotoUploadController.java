package com.orienteering.rest.demo.controller;

import org.springframework.web.multipart.MultipartFile;

public class ControlPhotoUploadController {

    private MultipartFile image;
    private int position;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
