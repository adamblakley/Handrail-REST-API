package com.orienteering.rest.demo.model;

/**
 * REST response on image save
 */
public class ImageUploadResponse {
    // was save successful
    private Boolean success;
    // filepath of saved image
    private String filepath;

    /**
     * Default constructor
     */
    public ImageUploadResponse() {
    }

    /**
     * Constructor with args
     * @param success
     * @param filepath
     */
    public ImageUploadResponse(Boolean success, String filepath) {
        this.success = success;
        this.filepath = filepath;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "ImageUploadResponse{" +
                "success=" + success +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
