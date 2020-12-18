package co.com.tallercloud.project.infraestructure.controller.java.models.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
@Getter
public class ProjectTaskConstants {

    public static final String DELETED="deleted";
    public static final String ERRORDUPLICATED="El proyecto ya existe. Ingrese un nuevo identificador de proyecto";
    public static final String ERRORDESC="Se ha presentando un error guardando en la base de datos";
    public static final String MODULE="projecTask";
    public static final String CODE400="400";
}
