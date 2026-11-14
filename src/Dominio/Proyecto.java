package Dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    //Atributos
    private int id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Empleado responsable;
    private List<Asignacion> asignaciones;

    //Constructor
    public Proyecto(int id, String nombre, LocalDate fechaInicio, LocalDate fechaFin, String estado, Empleado responsable) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.responsable = responsable;
        this.asignaciones = new ArrayList<Asignacion>();
    }
}
