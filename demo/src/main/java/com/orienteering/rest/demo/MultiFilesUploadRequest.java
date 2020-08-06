package com.orienteering.rest.demo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiFilesUploadRequest implements Serializable {

    private List<Integer> positions;
    private List<MultipartFile> files;

    public MultiFilesUploadRequest(List<Integer> positions, List<MultipartFile> files) {
        this.positions = positions;
        this.files = files;
    }

    public MultiFilesUploadRequest() {

    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFile(List<MultipartFile> files) {
        this.files = files;
    }
}
