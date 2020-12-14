package co.com.tallercloud.backlog.services;

import co.com.tallercloud.backlog.client.ProjectClient;
import co.com.tallercloud.backlog.domain.Backlog;
import co.com.tallercloud.backlog.model.Project;
import co.com.tallercloud.backlog.repository.BacklogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BacklogServiceImpl implements BacklogService {

    private final BacklogRepository backlogRepository;

    private final ProjectClient projectClient;

    public BacklogServiceImpl(BacklogRepository backlogRepository, ProjectClient projectClient) {
        this.backlogRepository = backlogRepository;
        this.projectClient = projectClient;
    }

    @Override
    public List<Backlog> getCustormerAll() {
        List<Backlog> backlogList = backlogRepository.findAll();
        backlogList.stream().map(backItem -> {
            Project project = projectClient.getProject(backItem.getProjectIdentifier()).getBody();
            backItem.setProject(project);
            return backItem;
        }).collect(Collectors.toList());
        return backlogRepository.findAll();
    }


    @Override
    public Backlog getCustomer(Long id) {
        Backlog backlog = backlogRepository.findById(id).orElse(null);
        if(backlog != null) {
            Project project = projectClient.getProject(backlog.getProjectIdentifier()).getBody();
            backlog.setProject(project);
        }
        return backlog;
    }


}
