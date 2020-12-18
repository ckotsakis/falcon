package com.worklogix.falcon.api;

import com.worklogix.falcon.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            projectService.createProject(projectName, projectDescription);
            //projectService.removeData("5fd6a3ad8120df4666ddc7a2", "d51afdbd-9e17-4833-980e-879cb4dd8e96");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public String getProjects(){
        return projectService.getProjects();
    }

    @GetMapping("/data")
    public String getData(@RequestParam("id") String id){
        return projectService.getData(id);
    }
}
