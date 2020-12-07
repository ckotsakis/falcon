package com.worklogix.falcon.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadService {
    //Deprecated?
    public void saveDataFile(MultipartFile dataFile) throws IOException;
}
