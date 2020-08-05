package com.cheng.controller;

import com.cheng.pojo.Projects;
import com.cheng.service.IProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AdminProjectController {

    @Resource
    private IProjectService projectService;

    @GetMapping("/getProjectss")
    public List<Projects> getAllProjects(){
        return projectService.getProject();
    }

    @GetMapping("/lookProjectByPid/{pid}")
    public Projects lookProjectByPid(@PathVariable int pid){
        return projectService.getProByPid(pid);
    }

    @GetMapping("/authProject/{pid}")
    public boolean authProject(@PathVariable int pid){
        return projectService.authProject(pid);
    }
}
