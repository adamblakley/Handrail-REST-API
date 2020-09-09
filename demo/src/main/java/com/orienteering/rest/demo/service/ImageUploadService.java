package com.orienteering.rest.demo.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.orienteering.rest.demo.model.ImageUploadProperties;
import com.orienteering.rest.demo.model.ImageUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Service saves Image Upload requests
 */
@Service
public class ImageUploadService {

    // amazonS3 client
    private AmazonS3 amazons3;
    // url of bucket
    @Value("${amazonProperties.endpointUrl}")
    private String url;
    // bucket name
    @Value("${amazonProperties.bucketName}")
    private String bucket;
    // bucket access key
    @Value("${amazonProperties.accessKey}")
    private String key;
    // bucket key secret
    @Value("${amazonProperties.secretKey}")
    private String secret;

    /**
     * Postconstruct called on creation
     * Initialises AmazonS3 client with credentials and region
     */
    @PostConstruct
    private void initialize() {
        AWSCredentials credentials = new BasicAWSCredentials(this.key, this.secret);
        this.amazons3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.EU_WEST_2).build();
    }

    /**
     * Take a multipart file and upload to server
     * Return image url or failure notification
     * @param multipartFile
     * @return
     */
    public ImageUploadResponse uploadFileRequest(MultipartFile multipartFile) {

        // file url of final image filepath
        String fileUrl = "";
        // convert multipart to file or return failure
        try {
            File file = convertToFile(multipartFile);
            String fileName = nameFile(multipartFile);
            fileUrl = url+"/"+bucket +"/"+fileName;
            System.out.println("File Length: "+fileName+" "+file.length());
            // request file upload to amazons3
            uploadFile(fileName, file);
            // delete remaining file
            file.delete();
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ImageUploadResponse(false,null);
        }
        return new ImageUploadResponse(true,fileUrl);
    }

    /**
     * Name file with date stamp and original filename
     * @param part
     * @return
     */
    private String nameFile(MultipartFile part) {
        Date now = Calendar.getInstance().getTime();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(now);
        return part.getOriginalFilename().replace(" ", "_")+timestamp+".jpg";
    }

    /**
     * Convert multipartfile to file via outputstream. Reverse of client operation
     * @param part
     * @return
     * @throws IOException
     */
    private File convertToFile(MultipartFile part) throws IOException {
        File file = new File(part.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(part.getBytes());
        fileOutputStream.close();
        return file;
    }

    /**
     * Request amazonS3 client uploads file to bucket
     * @param fileName
     * @param file
     */
    private void uploadFile(String fileName, File file) {
        amazons3.putObject(new PutObjectRequest(bucket, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

}
