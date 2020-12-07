package com.worklogix.falcon.dao;

import java.io.IOException;

public interface DataDao {
    public void importData(String fileName) throws IOException;
}
