package com.orienteering.rest.demo.service;

import com.orienteering.rest.demo.model.ImageUploadProperties;
import com.orienteering.rest.demo.model.ImageUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageUploadService {

    private final Path fileStorageLocation;

    @Autowired
    public ImageUploadService(ImageUploadProperties imageUploadProperties){
        this.fileStorageLocation = Paths.get(imageUploadProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            System.out.println("Unable to create directory");
            ex.printStackTrace();
        }
    }

    public ImageUploadResponse uploadImage(MultipartFile file){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(filename);
        try{
            Path copylocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(),copylocation, StandardCopyOption.REPLACE_EXISTING);

            return new ImageUploadResponse(true,"http://192.168.0.21:8080/uploads/photographs/"+file.getOriginalFilename());
        } catch (Exception e){
            System.out.println("Error Storing file");
            e.printStackTrace();
            return new ImageUploadResponse(true,null);
        }
    }

}
