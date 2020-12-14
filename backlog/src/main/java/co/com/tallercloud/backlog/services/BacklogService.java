package co.com.tallercloud.backlog.services;

import co.com.tallercloud.backlog.domain.Backlog;


import java.util.List;

public interface BacklogService {

    List<Backlog> getCustormerAll();

    Backlog getCustomer(Long id);


}
