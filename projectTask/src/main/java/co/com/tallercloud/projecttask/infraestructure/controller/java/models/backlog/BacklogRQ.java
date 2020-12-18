package co.com.tallercloud.projecttask.infraestructure.controller.java.models.backlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Setter
@Getter
public class BacklogRQ {

    @JsonProperty("data")
    @NotNull
    @Valid
    private List<BackLogRQAttributes> backLogRQAttributes;


}
