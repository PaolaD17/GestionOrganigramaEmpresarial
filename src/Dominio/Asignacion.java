package Dominio;

public class Asignacion {
    //Atributos
    private int id;
    private Empleado empleado;
    private Proyecto proyecto;
    private int horasAsignadas;
    private String rol; //Desarrollador, tester, lider, etc

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }

    public int getHorasAsignadas() { return horasAsignadas; }
    public void setHorasAsignadas(int horasAsignadas) { this.horasAsignadas = horasAsignadas; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    //Constructor
    public Asignacion(int id, Empleado empleado, Proyecto proyecto,  int horasAsignadas, String rol) {
        this.id = id;
        this.empleado = empleado;
        this.proyecto = proyecto;
        this.horasAsignadas = horasAsignadas;
        this.rol = rol;
    }

    public @Override String toString() {
        return "Asignacion {" +
                "id=" + id +
                ", empleado=" + empleado +
                ", proyecto=" + proyecto +
                ", horasAsignadas=" + horasAsignadas +
                ", rol='" + rol + '\'' +
                '}';
    }
}
