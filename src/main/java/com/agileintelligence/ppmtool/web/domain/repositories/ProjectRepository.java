package com.agileintelligence.ppmtool.web.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agileintelligence.ppmtool.web.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

}
