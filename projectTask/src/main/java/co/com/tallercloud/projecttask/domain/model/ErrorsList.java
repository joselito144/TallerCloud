package co.com.tallercloud.projecttask.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorsList {

    private List<StatusResponse> errorList=null;
}