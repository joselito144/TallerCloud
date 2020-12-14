package co.com.tallercloud.backlog.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Project {

    private Long id;

    private String projectName;

    private String description;

    private Date startDate;

    private Date endDate;

}
