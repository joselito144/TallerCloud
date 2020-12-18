package co.com.tallercloud.projecttask.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatusResponse {

    private String errorMessage;
    private String errorDesc;
    private String errorCode;
    private String module;
}