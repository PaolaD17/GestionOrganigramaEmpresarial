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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Empleado getResponsable() { return responsable; }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }


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
