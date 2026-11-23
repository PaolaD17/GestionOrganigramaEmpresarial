package Dominio;

public class Cargo {
    //Atributos
    private int id;
    private String nombre; //Gerente, líder, programador, etc
    private int nivelJerarquico; //1-Dirección, 2-Supervisor, 3-Operario
    private double salarioBase;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getNivelJerarquico() { return nivelJerarquico; }
    public void setNivelJerarquico(int nivelJerarquico) { this.nivelJerarquico = nivelJerarquico; }
    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    //Constructor
    public Cargo(int id, String nombre, int nivelJerarquico, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.nivelJerarquico = nivelJerarquico;
        this.salarioBase = salarioBase;
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
