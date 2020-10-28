package com.agileintelligence.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agileintelligence.ppmtool.web.domain.Project;
import com.agileintelligence.ppmtool.web.domain.repositories.services.MapValidationErrorService;
import com.agileintelligence.ppmtool.web.domain.repositories.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
    	
    	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
    	if(errorMap!=null) return errorMap;
    	
    	
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
    
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectId(@PathVariable String projectId){
    	
    	Project project = projectService.findProjectByIdentifier(projectId);
    	
    	return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public Iterable<Project> findAllProjects(){
    	return projectService.findAllProjects();
    	
    }
    
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
    	projectService.deleteProjectByIdentifier(projectId);
    	
    	return new ResponseEntity<String>("Project with ID'"+projectId+"' was deleted", HttpStatus.OK);
    }
    
}