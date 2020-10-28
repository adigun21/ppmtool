package com.agileintelligence.ppmtool.web.domain.repositories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agileintelligence.ppmtool.exceptions.ProjectIdException;
import com.agileintelligence.ppmtool.web.domain.Project;
import com.agileintelligence.ppmtool.web.domain.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        //Logic
    	try {
    		project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
    		return projectRepository.save(project);
    	}catch (Exception e) {
    		throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+" ' already exists");
    	}

    
    }

}