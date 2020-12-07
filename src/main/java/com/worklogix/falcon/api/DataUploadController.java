package com.worklogix.falcon.api;

import com.worklogix.falcon.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class DataUploadController {

    private final UploadService uploadService;

    @Autowired
    public DataUploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    @PostMapping("/dataUpload")
    public String dataUpload(@RequestParam("dataFile") MultipartFile dataFile, @RequestParam("tablename") String tablename) {
        String returnValue = "start";

        //System.out.println("We got a post!");
        //System.out.println(System.getProperty("user.home"));

        System.out.println(tablename);

        try {
            uploadService.saveDataFile(dataFile, tablename);
        } catch (IOException e) {
            e.printStackTrace();
            returnValue = "error";
        }

        return returnValue;
    }

}
