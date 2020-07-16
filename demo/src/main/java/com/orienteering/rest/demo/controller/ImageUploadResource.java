package com.orienteering.rest.demo.controller;

import com.orienteering.rest.demo.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ImageUploadResource {

    @Autowired
    ImageUploadService imageUploadService;

    @GetMapping("/")
    public String index(){
        return "upload";
    }

    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        imageUploadService.uploadImage(file);

        redirectAttributes.addFlashAttribute("message","You have successfully uploaded the image"+file.getOriginalFilename()+"!");

        return "redirect:/";
    }
}
