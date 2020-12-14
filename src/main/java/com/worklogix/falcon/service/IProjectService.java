package com.worklogix.falcon.service;

import java.io.IOException;

public interface IProjectService {
    public void createProject(String projectName, String projectDescription) throws IOException;
}
