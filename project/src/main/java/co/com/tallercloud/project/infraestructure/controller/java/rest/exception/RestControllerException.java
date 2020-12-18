package co.com.tallercloud.project.infraestructure.controller.java.rest.exception;


import co.com.tallercloud.project.domain.model.ErrorsList;
import co.com.tallercloud.project.domain.model.ProjectExceptionBadRequest;
import co.com.tallercloud.project.domain.model.ProjectExceptionNotFound;
import co.com.tallercloud.project.domain.model.StatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(annotations = RestController.class)
public class RestControllerException {

    @ExceptionHandler(ProjectExceptionBadRequest.class)
    public ResponseEntity<Object> handle(ProjectExceptionBadRequest exception){

        ErrorsList errorsList = new ErrorsList();
        List<StatusResponse> statusResponseList = new ArrayList<>();
        statusResponseList.add(exception.getStatus());
        errorsList.setErrorList(statusResponseList);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsList);

    }

    @ExceptionHandler(ProjectExceptionNotFound.class)
    public ResponseEntity<Object> handle(ProjectExceptionNotFound exception){

        ErrorsList errorsList = new ErrorsList();
        List<StatusResponse> statusResponseList = new ArrayList<>();
        statusResponseList.add(exception.getStatus());
        errorsList.setErrorList(statusResponseList);
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsList);

    }




}
