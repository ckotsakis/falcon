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
    public String dataUpload(@RequestParam("dataFile") MultipartFile dataFile,
                             @RequestParam("tablename") String tablename,
                             @RequestParam("desc") String desc,
                             @RequestParam("projectid") String id,
                             @RequestParam("techname") String techname) {

        String returnValue = "start";

        System.out.println(id);
        //System.out.println(System.getProperty("user.home"));

        //System.out.println(tablename);

        try {
            uploadService.saveDataFile(id, dataFile, tablename, desc, techname);
        } catch (IOException e) {
            e.printStackTrace();
            returnValue = "error";
        }

        return returnValue;
    }

    @GetMapping
    public String getData(@RequestParam("id") String id){

        return uploadService.getData(id);

    }

    @GetMapping("/getview")
    public String getView(@RequestParam("viewname") String viewName) {
        return uploadService.getDataView(viewName);
    }

    @DeleteMapping
    public void deleteData(@RequestParam("tablename") String tablename){
         uploadService.deleteData(tablename);
    }



}
