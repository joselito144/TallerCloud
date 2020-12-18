package co.com.tallercloud.backlog.model.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BacklogGenericExceptionNotFound extends RuntimeException {

    private static final long serialVersionUID = 2405172041950251807L;
    private final StatusResponse status;

}
