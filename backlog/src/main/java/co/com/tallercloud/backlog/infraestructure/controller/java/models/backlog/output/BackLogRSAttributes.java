package co.com.tallercloud.backlog.infraestructure.controller.java.models.backlog.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
public class BackLogRSAttributes {

  @JsonProperty("date")
  private String date;
  @JsonProperty("attributes")
  private BacklogRSAttributesData backlogRSAttributesData;

}
