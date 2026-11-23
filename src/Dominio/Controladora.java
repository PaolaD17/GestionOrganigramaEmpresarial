package Dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controladora {

    private static List<Empleado> aListaEmpleados = new ArrayList<>();
    private static List<Cargo> aListaCargos = new ArrayList<>();
    private static List<Departamento> aListaDepartamentos = new ArrayList<>();
    private static List<Proyecto> aListaProyectos = new ArrayList<>();
    private static List<Asignacion> aListaAsignaciones = new ArrayList<>();

    //EMPLEADO
    public boolean agregarEmpleado(Empleado empleado){
        if(buscarEmpleadoPorId(empleado.getId()) == null){
            aListaEmpleados.add(empleado);
            return true;
        }
        return false;
    }

    public boolean modificarEmpleado(int id, Cargo cargo, Departamento departamento, double salario) {
        Empleado empleadoAModificar = this.buscarEmpleadoPorId(id);
        if (empleadoAModificar != null) {
            empleadoAModificar.setCargo(cargo);
            empleadoAModificar.setDepartamento(departamento);
            empleadoAModificar.setSalario(salario);
            return true;
        }
        return false;
    }

    public boolean eliminarEmpleado(int id){
        Empleado empleadoAEliminar = buscarEmpleadoPorId(id);
        if(empleadoAEliminar != null && empleadoAEliminar.esHoja()) {
            aListaEmpleados.remove(empleadoAEliminar);
            return true;
        }
        return false;
    }

    public void MostrarArbol() {
        for (Empleado unEmpleado : aListaEmpleados) {
            // El Gerente General no tiene jefe
            if (unEmpleado.getJefe() == null) {
                System.out.println("===== ORGANIGRAMA =====");
                unEmpleado.mostrarArbol(0);

                System.out.println();
                System.out.println("========================================");
                System.out.println("ALTURA DEL ORGANIGRAMA: " + unEmpleado.alturaSubarbol());
                System.out.println("========================================");
                return;
            }
        }
        System.out.println("No hay empleados cargados.");
    }

    public boolean moverEmpleado(int idEmpleado, int idNuevoJefe) {
        Empleado empleadoAMover = buscarEmpleadoPorId(idEmpleado);
        Empleado nuevoJefe = buscarEmpleadoPorId(idNuevoJefe);
        if (empleadoAMover == null || nuevoJefe == null) {
            return false;
        }
        if (empleadoAMover.getJefe() == null) {
            return false; // no mover Gerente General
        }
        empleadoAMover.moverA(nuevoJefe);
        return true;
    }

    public Empleado buscarEmpleadoPorId(int id){
        for(Empleado unEmpleado : aListaEmpleados){
            if(unEmpleado.getJefe() == null){
                return unEmpleado.buscarPorId(id);
            }
        }
        return null;
    }

    public List<Empleado> buscarEmpleadoPorApellido(String apellido){
        List<Empleado> encontrados = new ArrayList<>();
        for (Empleado unEmpleado : aListaEmpleados) {
            if (unEmpleado.getJefe() == null) {
                unEmpleado.buscarPorApellido(
                        apellido,
                        encontrados
                );
                return encontrados;
            }
        }
        return encontrados;
    }

    public void recorridoPorNiveles(int nivelBuscado) {
        for (Empleado unEmpleado : aListaEmpleados) {
            if (unEmpleado.getJefe() == null) {
                Empleado.recorridoPorNiveles(
                        unEmpleado,
                        nivelBuscado
                );
                return;
            }
        }
    }

    public boolean existeNivel(int nivelBuscado){
        for(Empleado unEmpleado : aListaEmpleados){
            if(unEmpleado.getNivel() == nivelBuscado){
                return true;
            }
        }
        return false;
    }

    public List<Empleado> ListaEmpleados(){
        return aListaEmpleados;
    }

    //CARGO
    public boolean agregarCargo(Cargo cargo){
        if(buscarCargoPorId(cargo.getId()) == null){
            aListaCargos.add(cargo);
            return true;
        }
        return false;
    }

    public Cargo buscarCargo(int id) {
        for (Cargo c : aListaCargos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Cargo> ListaCargos(){
        return aListaCargos;
    }

    public Cargo buscarCargoPorId(int id) {
        for (Cargo cargo : aListaCargos) {
            if (cargo.getId() == id) {
                return cargo;
            }
        }
        return null;
    }

    public Departamento buscarDepartamento(int id) {
        for (Departamento d : aListaDepartamentos) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    //DEPARTAMENTO
    public List<Departamento> ListaDepartamentos(){
        return aListaDepartamentos;
    }

    public Departamento buscarDepartamentoPorId(int id) {
        for (Departamento departamento : aListaDepartamentos) {
            if (departamento.getId() == id) {
                return departamento;
            }
        }
        return null;
    }

    // CARGO DATOS
    public static void CargoDatos() {
        // =========================
        // CARGOS
        // =========================
        Cargo gerenteGeneral = new Cargo(1, "Gerente General", 1, 150000);
        Cargo gerenteTecnologia = new Cargo(2, "Gerente Tecnología", 2, 120000);
        Cargo liderDesarrollo = new Cargo(3, "Líder Desarrollo", 3, 90000);
        Cargo programador = new Cargo(4, "Programador", 4, 60000);
        Cargo liderInfraestructura = new Cargo(5, "Líder Infraestructura", 3, 90000);
        Cargo administradorRedes = new Cargo(6, "Administrador Redes", 4, 65000);
        Cargo gerenteRRHH = new Cargo(7, "Gerente RRHH", 2, 120000);
        Cargo analistaRRHH = new Cargo(8, "Analista RRHH", 4, 70000);

        aListaCargos.add(gerenteGeneral);
        aListaCargos.add(gerenteTecnologia);
        aListaCargos.add(liderDesarrollo);
        aListaCargos.add(programador);
        aListaCargos.add(liderInfraestructura);
        aListaCargos.add(administradorRedes);
        aListaCargos.add(gerenteRRHH);
        aListaCargos.add(analistaRRHH);

        // =========================
        // DEPARTAMENTOS
        // =========================
        Departamento direccion = new Departamento(1,"Dirección","Gestión general de la empresa",null);
        Departamento tecnologia = new Departamento(2,"Tecnología","Desarrollo e infraestructura",null);
        Departamento rrhh = new Departamento(3,"Recursos Humanos","Gestión del personal",null);

        aListaDepartamentos.add(direccion);
        aListaDepartamentos.add(tecnologia);
        aListaDepartamentos.add(rrhh);

        // =========================
        // EMPLEADOS
        // =========================
        Empleado laura = new Empleado(1,"Laura","Méndez", gerenteGeneral, direccion,150000, LocalDate.of(2020,1,15));
        Empleado diego = new Empleado(2,"Diego","Pérez", gerenteTecnologia, tecnologia,120000, LocalDate.of(2020,3,10));
        Empleado ana = new Empleado(3,"Ana","Rodríguez",liderDesarrollo,tecnologia,90000, LocalDate.of(2021,5,20));
        Empleado martin = new Empleado(4,"Martín","Silva",programador,tecnologia,60000, LocalDate.of(2022,2,1));
        Empleado sofia = new Empleado(5,"Sofía","Castro",programador,tecnologia,60000, LocalDate.of(2022,7,15));
        Empleado pablo = new Empleado(6,"Pablo","Gómez",liderInfraestructura,tecnologia,90000, LocalDate.of(2021,8,12));
        Empleado lucia = new Empleado(7,"Lucía","Fernández",administradorRedes,tecnologia,65000, LocalDate.of(2022,9,5));
        Empleado fernando = new Empleado(8,"Fernando","López",gerenteRRHH,rrhh,120000, LocalDate.of(2020,2,8));
        Empleado camila = new Empleado(9,"Camila","Torres",analistaRRHH,rrhh,70000, LocalDate.of(2022,11,30));

        aListaEmpleados.add(laura);
        aListaEmpleados.add(diego);
        aListaEmpleados.add(ana);
        aListaEmpleados.add(martin);
        aListaEmpleados.add(sofia);
        aListaEmpleados.add(pablo);
        aListaEmpleados.add(lucia);
        aListaEmpleados.add(fernando);
        aListaEmpleados.add(camila);

        // =========================
        // RESPONSABLES
        // =========================
        direccion.setResponsable(laura);
        tecnologia.setResponsable(diego);
        rrhh.setResponsable(fernando);

        // =========================
        // ORGANIGRAMA
        // =========================
        laura.agregarSubordinado(diego);
        laura.agregarSubordinado(fernando);
        diego.agregarSubordinado(ana);
        diego.agregarSubordinado(pablo);
        ana.agregarSubordinado(martin);
        ana.agregarSubordinado(sofia);
        pablo.agregarSubordinado(lucia);
        fernando.agregarSubordinado(camila);

        // =========================
        // PROYECTOS
        // =========================
        Proyecto sistemaRRHH = new Proyecto(1,"Sistema RRHH",LocalDate.of(2024,1,1),LocalDate.of(2024,12,31),"En ejecución", diego);
        Proyecto portalClientes = new Proyecto(2,"Portal Clientes",LocalDate.of(2024,3,1),LocalDate.of(2024,11,30), "En ejecución", ana);
        Proyecto infraestructura = new Proyecto(3, "Modernización Infraestructura", LocalDate.of(2024,2,1), LocalDate.of(2024,10,30), "Planificado", pablo);

        aListaProyectos.add(sistemaRRHH);
        aListaProyectos.add(portalClientes);
        aListaProyectos.add(infraestructura);

        // =========================
        // ASIGNACIONES
        // =========================
        aListaAsignaciones.add(new Asignacion(1,martin,sistemaRRHH, 40,"Desarrollador"));
        aListaAsignaciones.add(new Asignacion(2,sofia,sistemaRRHH, 35,"Tester"));
        aListaAsignaciones.add(new Asignacion(3,ana,portalClientes, 20,"Líder Técnico"));
        aListaAsignaciones.add(new Asignacion(4,lucia,infraestructura, 30,"Administrador de Redes"));
        aListaAsignaciones.add(new Asignacion(5,camila,sistemaRRHH, 15,"Analista Funcional"));
    }
}
