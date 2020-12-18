package co.com.tallercloud.projecttask.infraestructure.services.test;

import co.com.tallercloud.projecttask.domain.entities.ProjectTask;
import co.com.tallercloud.projecttask.domain.model.ProjectExceptionBadRequest;
import co.com.tallercloud.projecttask.domain.model.ProjectTaskDao;
import co.com.tallercloud.projecttask.infraestructure.services.ProjectTaskServiceImpl;
import co.com.tallercloud.projecttask.infraestructure.services.repository.ProjectTaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ContextConfiguration(classes = {ProjectTaskServiceImpl.class})
public class ProjectTaskServiceImplTest  {


    @MockBean
    private  ProjectTaskRepository projectTaskRepository;

    private ProjectTaskDao projectTaskDao ;
    private ProjectTaskServiceImpl projectTaskService;


    @Before
    public void before(){
        projectTaskRepository = Mockito.mock(ProjectTaskRepository.class);
        projectTaskService = new ProjectTaskServiceImpl(projectTaskRepository);
        projectTaskDao=new ProjectTaskDao();
        projectTaskDao.setAcceptanceCriteria("Criterio  de prueba");
        projectTaskDao.setDescription("test");
        projectTaskDao.setEndDate(new Date(0));
        projectTaskDao.setStartDate(new Date(0));
        projectTaskDao.setHours(1.2);
        projectTaskDao.setName("Project test");
        projectTaskDao.setPriority(1);
        projectTaskDao.setStatus("in progress");
        projectTaskDao.setProjectIdentifier("4454");
        projectTaskDao.setSummary("summary");
        projectTaskDao.setId(1L);

    }


    @Test
    public void saveProjectTaskSucces(){
        ProjectTask projectTask=new ProjectTask();
        BeanUtils.copyProperties(projectTaskDao,projectTask);
        doReturn(projectTask)
                .when(projectTaskRepository).save(Mockito.any());

        ProjectTaskDao response = projectTaskService.createTask(projectTaskDao);
        assertEquals("in progress",response.getStatus());
        assertEquals(java.util.Optional.of(1.2).get(), java.util.Optional.ofNullable(response.getHours()).get());
        assertEquals(java.util.Optional.of(1L).get(), java.util.Optional.ofNullable(response.getId()).get());
    }

    @Test()
    public void saveProjectTaskError(){
        ProjectTask projectTask=new ProjectTask();
        BeanUtils.copyProperties(projectTaskDao,projectTask);
        doThrow(new DataIntegrityViolationException("Error"))
                .when(projectTaskRepository).save(Mockito.any());


        try{
            projectTaskService.createTask(projectTaskDao);
        }catch (ProjectExceptionBadRequest exceptionBadRequest){
            assertEquals("400",exceptionBadRequest.getStatus().getErrorCode());
            assertEquals("projecTask",exceptionBadRequest.getStatus().getModule());
            assertEquals("Se ha presentando un error registrando la tarea en la base de datos",
                    exceptionBadRequest.getStatus().getErrorMessage());
        }


    }




}
