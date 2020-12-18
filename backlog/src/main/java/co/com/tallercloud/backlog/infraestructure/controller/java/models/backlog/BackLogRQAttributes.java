package co.com.tallercloud.backlog.infraestructure.controller.java.models.backlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Setter
@Getter
public class BackLogRQAttributes {

  @Valid
  @JsonProperty("attributes")
  private BacklogRQAttributesData backlogRQAttributesData;

}
