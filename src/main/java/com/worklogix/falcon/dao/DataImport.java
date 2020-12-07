package com.worklogix.falcon.dao;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Repository("mongoDao")
public class DataImport implements DataDao{

    @Override
    public void importData(String fileName) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = input.readLine()) != null){
            System.out.println(line);
        }
    }

}
