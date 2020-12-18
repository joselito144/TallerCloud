package co.com.tallercloud.backlog.infraestructure.services.gateway;

import co.com.tallercloud.backlog.domain.entities.Backlog;

public interface BackLogService {

    Backlog getBackLog(String  projectIdentifier);
    Backlog saveBacklog(String projectIdentifier);




}
