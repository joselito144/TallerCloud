package co.com.tallercloud.backlog.infraestructure.controller.java.models.projectTask.output;

        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.sun.istack.NotNull;
        import lombok.Getter;
        import lombok.Setter;
        import org.springframework.validation.annotation.Validated;

        import java.util.List;

@Validated
@Setter
@Getter

public class ProjectTaskRS {

    @JsonProperty("data")
    @NotNull
    private List<ProjectTaskRSAttributes> projectTaskRSAttributes;


}
