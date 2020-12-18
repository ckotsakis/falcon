package com.worklogix.falcon.dao;

import java.io.IOException;

public interface DataDao {
    public void importData(String id, String fileName, String tableName, String desc, String techName) throws IOException;

    public String getData(String tableName);

    public void deleteData(String tableName);
}
