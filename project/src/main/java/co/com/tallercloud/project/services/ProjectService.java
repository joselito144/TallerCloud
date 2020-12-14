package co.com.tallercloud.project.services;

import co.com.tallercloud.project.domain.Project;


import java.util.List;

public interface ProjectService {

    List<Project> getProyectsAll();

    Project getProyect(Long id);

    Project getProjectByIdenfier(String projectIdenfier);


}
