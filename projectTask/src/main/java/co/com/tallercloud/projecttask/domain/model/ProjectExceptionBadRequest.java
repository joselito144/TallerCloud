package co.com.tallercloud.projecttask.domain.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ProjectExceptionBadRequest extends RuntimeException {

    private static final long serialVersionUID = 2405172041950251807L;
    private final StatusResponse status;

}
