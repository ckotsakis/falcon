package com.worklogix.falcon.api;

import com.worklogix.falcon.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/dataUpload")
public class DataUploadController {

    private final UploadService uploadService;

    @Autowired
    public DataUploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    @PostMapping
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

    @GetMapping
    public String getData(@RequestParam("tablename") String tablename){
        return uploadService.getData(tablename);

    }



}
