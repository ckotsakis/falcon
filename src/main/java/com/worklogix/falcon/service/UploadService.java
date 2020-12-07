package com.worklogix.falcon.service;

import com.worklogix.falcon.dao.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.worklogix.falcon.dao.DataImport;

@Service
public class UploadService {

    private final DataDao dataDao;

    @Autowired
    public UploadService(@Qualifier("mongoDao") DataDao dataDao) {
        this.dataDao = dataDao;
    }

    public void saveDataFile(MultipartFile dataFile) throws IOException {
        String folder = System.getProperty("user.home") + "/Downloads/";

        byte[] bytes = dataFile.getBytes();
        Path path = Paths.get(folder + dataFile.getOriginalFilename());
        //Write the file locally so we can read it into a database
        Files.write(path, bytes);
        dataDao.importData(folder.concat(dataFile.getOriginalFilename()));


    }
}
