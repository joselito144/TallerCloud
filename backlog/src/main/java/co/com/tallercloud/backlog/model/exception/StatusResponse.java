package co.com.tallercloud.backlog.model.exception;

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