package com.worklogix.falcon.api;

import com.worklogix.falcon.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class DataUploadController {

    @PostMapping("/dataUpload")
    public String dataUpload(@RequestParam("dataFile") MultipartFile dataFile) {
        String returnValue = "start";

        System.out.println("We got a post!");

        System.out.println(System.getProperty("user.home"));

        try {
            UploadService upload = new UploadService();
            upload.saveDataFile(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
            returnValue = "error";
        }

        return returnValue;
    }

}
