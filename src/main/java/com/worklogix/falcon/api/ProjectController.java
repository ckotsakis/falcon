package com.worklogix.falcon.api;

import com.worklogix.falcon.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping
    public void createProject(@RequestParam("projectName") String projectName,
                              @RequestParam("projectDesc") String projectDescription){
        try {
            //projectService.createProject(projectName, projectDescription);
            //projectService.addData("5fd6a3ad8120df4666ddc7a2", "Anything", "Describing anything", "home/downloads/fakefile");
            projectService.removeData("5fd6a3ad8120df4666ddc7a2", "ce51245e-d830-4d6f-9998-039d9fa06f93");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
