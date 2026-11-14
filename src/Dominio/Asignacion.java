package Dominio;

public class Asignacion {
    //Atributos
    private int id;
    private Empleado empleado;
    private Proyecto proyecto;
    private int horasAsignadas;
    private String rol;

    //Constructor
    public Asignacion(int id, Empleado empleado, Proyecto proyecto,  int horasAsignadas, String rol) {
        this.id = id;
        this.empleado = empleado;
        this.proyecto = proyecto;
        this.horasAsignadas = horasAsignadas;
        this.rol = rol;
    }
}
