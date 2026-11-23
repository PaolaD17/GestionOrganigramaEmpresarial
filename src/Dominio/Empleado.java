package Dominio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Empleado {
    //Atributos
    private int id;
    private String nombre;
    private String apellido;
    private Cargo cargo;
    private Departamento departamento;
    private double salario;
    private LocalDate fechaIngreso;
    private Empleado jefe; //Null si es el Gerente General
    private List<Empleado> subordinados; //Lista de empleados a su cargo (hijos en el árbol)
    private int nivel; //Profundidad en el árbol (0 para la raíz)

    //Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public Empleado getJefe() { return jefe; }
    public void setJefe(Empleado jefe) { this.jefe = jefe; }
    public List<Empleado> getSubordinados() { return subordinados; }
    public void setSubordinados(List<Empleado> subordinados) { this.subordinados = subordinados; }
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) {
        this.nivel = nivel;
        for (Empleado sub : subordinados) {
            sub.setNivel(nivel + 1);
        }
    }

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
        this.nivel = 0;
    }

    //ToString
    public @Override String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre + " " + apellido +
                " | Cargo: " + cargo.getNombre() +
                " | Nivel: " + nivel;
    }

    //AGREGAR UN HIJO (un subordinado) al nodo
    public void agregarSubordinado(Empleado e) {
        e.jefe = this;                 // yo soy el jefe del nuevo empleado
        e.setNivel(this.nivel + 1);
        this.subordinados.add(e);      // lo agrego a mi lista de hijos
    }

    //ELIMINAR UN HIJO (un subordinado) del nodo
    public boolean eliminarSubordinado(int id) {
        for (int i = 0; i < subordinados.size(); i++) {
            if (subordinados.get(i).getId() == id) {
                subordinados.remove(i);
                return true;
            }
        }
        return false;
    }

    //MOVER UN HIJO (un subordinado) del árbol
    public void moverA(Empleado nuevoJefe) {
        if (this.jefe != null) {
            this.jefe.eliminarSubordinado(this.id);
        }
        nuevoJefe.agregarSubordinado(this);
    }

    // true si el nodo no tiene hijos (es una hoja del árbol)
    public boolean esHoja() {
        return this.subordinados.isEmpty();
    }

    //  RECORRIDO: mostrar el organigrama (pre-orden, indentado)
    //  Se visita primero el nodo y luego, recursivamente, cada hijo.
    public void mostrarArbol(int indent) {
        String sangria = "    ".repeat(indent);
        System.out.println(
                sangria +
                        "👤 " +
                        this.nombre + " " +
                        this.apellido +
                        " [ID: " + this.id + "]" +
                        " (Nivel " + this.nivel + ")" +
                        " - " + cargo.getNombre() +
                        " - Subordinados: " +
                        this.contarSubordinados()
        );
        for (Empleado sub : subordinados) {
            sub.mostrarArbol(indent + 1);
        }
    }

    // BÚSQUEDA recursiva por ID
    public Empleado buscarPorId(int idBuscado) {
        if (this.id == idBuscado) {
            return this;
        }
        for (Empleado sub : subordinados) {
            Empleado encontrado = sub.buscarPorId(idBuscado);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }

    // RECORRIDO de empleados por niveles
    public static void recorridoPorNiveles(Empleado raiz, int nivelBuscado) {
        Queue<Empleado> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            Empleado actual = cola.poll();
            if (actual.getNivel() == nivelBuscado) {
                System.out.println(
                        "ID: " + actual.getId()
                                + " | "
                                + actual.getNombre() + " "
                                + actual.getApellido()
                                + " | Cargo: "
                                + actual.getCargo().getNombre()
                );
            }
            for (Empleado hijo : actual.getSubordinados()) {
                cola.add(hijo);
            }
        }
    }

    //  CONTAR subordinados totales (directos + indirectos) = cantidad de nodos que cuelgan de este, sin contarse a sí mismo.
    public int contarSubordinados() {
        int total = subordinados.size();           // mis hijos directos
        for (Empleado sub : subordinados) {
            total += sub.contarSubordinados();      // + los subordinados de cada hijo
        }
        return total;
    }

    //  SUMAR la masa salarial de un área = mi salario + el de toda mi descendencia.
    public double sumarMasaSalarial() {
        double total = this.salario;               // mi propio salario
        for (Empleado sub : subordinados) {
            total += sub.sumarMasaSalarial();      // + el de cada subárbol
        }
        return total;
    }

    //  ALTURA del subárbol (cantidad de niveles bajo este nodo)
    public int alturaSubarbol() {
        if (esHoja()) {
            return 0;                              // caso base
        }
        int maxAltura = 0;
        for (Empleado sub : subordinados) {
            int altura = sub.alturaSubarbol();
            if (altura > maxAltura) {
                maxAltura = altura;                // me quedo con la rama más profunda
            }
        }
        return maxAltura + 1;
    }

    //  CADENA DE MANDO: ruta desde este empleado hasta la raíz
    public String cadenaDeMando() {
        String cadena = this.cargo + " (" + nombre + ")";
        Empleado actual = this.jefe;
        while (actual != null) {
            cadena += "  <-  " + actual.cargo + " (" + actual.nombre + ")";
            actual = actual.jefe;
        }
        return cadena;
    }
}
