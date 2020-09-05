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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Service saves Image Upload requests
 */
@Service
public class ImageUploadService {

    // file path of saved image
    private final Path fileStorageLocation;

    /**
     * Create directory if doesnt exist via imageUploadProperties
     * @param imageUploadProperties
     */
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

    /**
     * Save image
     * @param file
     * @return
     */
    public ImageUploadResponse uploadImage(MultipartFile file){
        // get file name
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        // get date
        Date now = Calendar.getInstance().getTime();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(now);
        // save file to directory with timestamp
        try{
            Path copylocation = this.fileStorageLocation.resolve(filename+timestamp+".jpg");
            Files.copy(file.getInputStream(),copylocation, StandardCopyOption.REPLACE_EXISTING);
            return new ImageUploadResponse(true,"http://192.168.0.21:8080/uploads/photographs/"+file.getOriginalFilename()+timestamp+".jpg");
        } catch (Exception e){
            System.out.println("Error Storing file");
            e.printStackTrace();
            return new ImageUploadResponse(true,null);
        }
    }

}
