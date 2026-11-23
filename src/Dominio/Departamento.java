package Dominio;

public class Departamento {
    //Atributos
    private int id;
    private String nombre; //Tecnología, Comercial, RRHH, etc.
    private String descripcion;
    private Empleado responsable; //Empleado a cargo del departamento

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Empleado getResponsable() { return responsable; }
    public void setResponsable(Empleado responsable) { this.responsable = responsable; }

    //Constructor
    public Departamento(int id, String nombre, String descripcion, Empleado responsable) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + descripcion;
    }
}
