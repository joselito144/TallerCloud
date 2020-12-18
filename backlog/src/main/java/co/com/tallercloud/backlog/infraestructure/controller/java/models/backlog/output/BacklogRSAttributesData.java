package co.com.tallercloud.backlog.infraestructure.controller.java.models.backlog.output;

import co.com.tallercloud.backlog.domain.entities.Backlog;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BacklogRSAttributesData {


    @JsonProperty("backlog")
    private Backlog backlog;


}
