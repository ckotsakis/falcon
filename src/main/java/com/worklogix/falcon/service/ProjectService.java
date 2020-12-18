package com.worklogix.falcon.service;

import com.worklogix.falcon.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

//TODO fix the interface
@Service
public class ProjectService implements IProjectService {

    private final ProjectDao projectDao;

    @Autowired
    public ProjectService(ProjectDao projectDao){
        this.projectDao = projectDao;
    }

    @Override
    public void createProject(String projectName, String projectDescription) throws IOException {
        projectDao.createProject(projectName, projectDescription);
    }

    public String getProjects(){
        return projectDao.getProjects();
    }

    public String getData(String id){
        return projectDao.getData(id);
    }

    public void addData(String id, String name, String description, String fileName, String techName) throws  IOException{
        projectDao.addData(id, name, description, fileName, techName);
    }

    public void removeData(String id, String uuid) throws  IOException{
        projectDao.removeData(id, uuid);
    }


}
