package com.worklogix.falcon.dao;

import java.io.IOException;

public interface DataDao {
    public void importData(String id, String fileName, String tableName, String desc) throws IOException;

    public String getData(String tableName);
}
