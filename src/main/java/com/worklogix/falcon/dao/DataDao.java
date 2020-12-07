package com.worklogix.falcon.dao;

import java.io.IOException;

public interface DataDao {
    public void importData(String fileName, String tableName) throws IOException;

    public String getData(String tableName);
}
