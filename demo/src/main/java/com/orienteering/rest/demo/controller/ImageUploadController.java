package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.model.EventPhotograph;
import com.orienteering.rest.demo.model.ImageUploadResponse;
import com.orienteering.rest.demo.model.StatusResponseEntity;
import com.orienteering.rest.demo.service.EventPhotographService;
import com.orienteering.rest.demo.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ImageUploadController {

    @Autowired
    ImageUploadService imageUploadService;

    @Autowired
    EventPhotographService eventPhotographService;

    @Autowired

    @GetMapping("/")
    public String index(){
        return "upload";
    }


    @PostMapping("/uploadeventimage")
    public ResponseEntity<StatusResponseEntity<Boolean>> uploadImage(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){

        ImageUploadResponse imageUploadResponse = imageUploadService.uploadImage(file);

        if(imageUploadResponse.getSuccess()){
            EventPhotograph eventPhotograph = new EventPhotograph();
            eventPhotograph.setPhotoName("imageUploadRequest.getName()");
            eventPhotograph.setPhotoPath(imageUploadResponse.getFilepath());
            if(eventPhotographService.saveEventPhotograph(eventPhotograph)){
                return  new ResponseEntity( new StatusResponseEntity(true, "Image upload successful: "+eventPhotograph.getPhotoPath(),false), HttpStatus.OK);
            } else {
                return  new ResponseEntity( new StatusResponseEntity(false, "Image entity failure",false), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return  new ResponseEntity( new StatusResponseEntity(false, "Image upload failed",false), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
