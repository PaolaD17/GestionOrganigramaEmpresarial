package Dominio;

public class Cargo {
    //Atributos
    private int id;
    private String nombre;
    private int nivelJerarquico;
    private double salarioBase;

    //Constructor
    public Cargo(int id, String nombre, int nivelJerarquico, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.nivelJerarquico = nivelJerarquico;
        this.salarioBase = salarioBase;
    }

}
