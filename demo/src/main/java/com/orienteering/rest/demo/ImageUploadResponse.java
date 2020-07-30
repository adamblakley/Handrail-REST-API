package com.orienteering.rest.demo;

public class ImageUploadResponse {

    private Boolean success;
    private String filepath;

    public ImageUploadResponse() {
    }

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
