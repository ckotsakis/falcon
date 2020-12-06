package com.worklogix.falcon.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadService implements IUploadService {

    @Override
    public void saveDataFile(MultipartFile dataFile) throws IOException {
        String folder = System.getProperty("user.home") + "/Downloads/";

        byte[] bytes = dataFile.getBytes();
        Path path = Paths.get(folder + dataFile.getOriginalFilename());
        //Write the file locally so we can read it into a database
        Files.write(path, bytes);

    }
}
