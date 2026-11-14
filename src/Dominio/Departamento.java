package Dominio;

public class Departamento {
    //Atributos
    private int id;
    private String nombre;
    private String descripcion;
    private Empleado responsable;

    //Constructor
    public Departamento(int id, String nombre, String descripcion, Empleado responsable) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.responsable = responsable;
    }
}
