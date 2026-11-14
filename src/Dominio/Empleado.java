package Dominio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empleado {
    //Atributos
    private int id;
    private String nombre;
    private String apellido;
    private Cargo cargo;
    private Departamento departamento;
    private double salario;
    private LocalDate fechaIngreso;
    private Empleado jefe;
    private List<Empleado> subordinados;

    //Constructor
    public Empleado(int id, String nombre, String apellido, Cargo cargo, Departamento departamento, double salario, LocalDate fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.jefe = null;
        this.subordinados = new ArrayList<Empleado>();
    }

    // ===========================================
    //  AGREGAR UN HIJO (un subordinado) al nodo
    // ===========================================
    public void agregarSubordinado(Empleado e){
        e.jefe = this;
        this.subordinados.add(e);
    }

    // true si el nodo no tiene hijos (es una hoja del árbol)
    public boolean esHoja(){
        return this.subordinados.isEmpty();
    }

    // ==============================================================
    //  RECORRIDO: mostrar el organigrama (pre-orden, indentado)
    //  Se visita primero el nodo y luego, recursivamente, cada hijo.
    // ==============================================================
    public void mostrarOrganigrama(int nivel){
        //sangría segúnla profundidad del árbol
        for(int i = 0; i < nivel; i++){
            System.out.println("    ");
        }
        System.out.println("- " + nombre + " " + apellido+ " (" + cargo + ")");

        // caso recursivo: lo mismo para cada subordinado, un nivel más abajo
        for (Empleado sub : subordinados) {
            sub.mostrarOrganigrama(nivel + 1);
        }
    }


}
