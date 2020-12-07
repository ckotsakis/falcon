package com.worklogix.falcon.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataImport {

    public void importData(String fileName) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = input.readLine()) != null){
            System.out.println(line);
        }
    }

}
