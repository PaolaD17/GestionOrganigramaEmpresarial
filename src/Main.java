import Dominio.*;
import java.util.*;

public static void main(String[] args) {
    String opcion = "";
    Controladora unaControladora = new Controladora();

    unaControladora.CargoDatos();

    while(!opcion.equals("0")){
        System.out.println("");
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║                    NOVATECH                   ║");
        System.out.println("║ Sistema de Gestión de Organigrama Empresarial ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   1- Empleados                                ║");
        System.out.println("║   2- Cargos                                   ║");
        System.out.println("║   3- Departamentos                            ║");
        System.out.println("║   4- Proyectos                                ║");
        System.out.println("║   5- Asignaciones                             ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   0- Salir                                    ║");
        System.out.println("╚═══════════════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
            case "1":
                MenuEmpleados();
                break;
            case "2":
                MenuCargos();
                break;
            case "3":
                MenuDepartamentos();
                break;
            case "4":
                MenuProyectos();
                break;
            case "5":
                MenuAsignaciones();
                break;
        }
    }
    System.out.println("");
    System.out.println("<--- GRACIAS POR ELEGIRNOS!!! --->");
}

// Espera un enter para volver al menú
public static void Pausa(){
    System.out.println("");
    System.out.println("Para volver presione enter");
    Scanner keyboard = new Scanner(System.in);
    keyboard.nextLine();
}

//============================================
//||        SECTOR E M P L E A D O S        ||
//============================================
public static void MenuEmpleados(){
    String opcion = "";
    while(!opcion.equals("0")){
        System.out.println("");
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   Empleados                                   ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   1- Agregar Empleado                         ║");
        System.out.println("║   2- Modificar Empleado                       ║");
        System.out.println("║   3- Eliminar Empleado                        ║");
        System.out.println("║   4- Ver Árbol de Empleados                   ║");
        System.out.println("║   5- Mover Empleado                           ║");
        System.out.println("║   6- Buscar Empleado por ID                   ║");
        System.out.println("║   7- Buscar Empleado por Apellido             ║");
        System.out.println("║   8- Listar Empleados por Nivel Jerárquico    ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   0- Volver                                   ║");
        System.out.println("╚═══════════════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
            case "1":
                AgregarEmpleado();
                break;
            case "2":
                ModificarEmpleado();
                break;
            case "3":
                EliminarEmpleado();
                break;
            case "4":
                MostrarArbol();
                break;
            case "5":
                MoverEmpleado();
                break;
            case "6":
                BuscarEmpleadoPorId();
                break;
            case "7":
                BuscarEmpleadoPorApellido();
                break;
            case "8":
                EmpleadosPorNivel();
            break;
        }
    }
}
public static void AgregarEmpleado(){
    Controladora unaControladora = new Controladora();

    System.out.println("Ingresar una Empleado");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();
    if (unaControladora.buscarEmpleadoPorId(id) != null) {
        System.out.println("Ya existe un empleado con ese ID.");
        return;
    }

    keyboard.nextLine();
    System.out.println("Ingrese el Nombre: ");
    String nombre = keyboard.nextLine();

    System.out.println("Ingrese el Apellido: ");
    String apellido = keyboard.nextLine();

    System.out.println("CARGOS DISPONIBLES:");
    for (Cargo cargo : unaControladora.ListaCargos()) {
        System.out.println(cargo);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del cargo:");
    int idCargo = keyboard.nextInt();
    Cargo cargo = unaControladora.buscarCargoPorId(idCargo);
    if (cargo == null) {
        System.out.println("Cargo no encontrado.");
        return;
    }
    if (cargo.getNombre().equalsIgnoreCase("Gerente General")) {
        for (Empleado emp : unaControladora.ListaEmpleados()) {
            if (emp.getCargo().getNombre().equalsIgnoreCase("Gerente General")) {
                System.out.println("Ya existe un Gerente General en la empresa.");
                return;
            }
        }
    }

    System.out.println("DEPARTAMENTOS DISPONIBLES:");
    for (Departamento dep : unaControladora.ListaDepartamentos()) {
        System.out.println(dep);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del departamento:");
    int idDepartamento = keyboard.nextInt();
    Departamento departamento = unaControladora.buscarDepartamentoPorId(idDepartamento);
    if (departamento == null) {
        System.out.println("Departamento no encontrado.");
        return;
    }

    System.out.println("Ingrese el salario: ");
    Double salario = keyboard.nextDouble();
    keyboard.nextLine();

    System.out.println("Ingrese la fecha de ingreso (yyyy-MM-dd): ");
    LocalDate fechaIngreso = LocalDate.parse(keyboard.nextLine());

    System.out.println("JEFES DISPONIBLES:");
    for (Empleado unEmp : unaControladora.ListaEmpleados()) {

        System.out.println(
                "ID: " + unEmp.getId() +
                        " | " + unEmp.getNombre() + " " + unEmp.getApellido() +
                        " | Cargo: " + unEmp.getCargo().getNombre()
        );
    }
    System.out.println("Ingrese el ID del jefe: ");
    int idJefe = keyboard.nextInt();
    keyboard.nextLine();

    Empleado unEmpleado = new Empleado(id, nombre, apellido, cargo, departamento, salario, fechaIngreso);

    if(idJefe != 0){
        Empleado jefe = unaControladora.buscarEmpleadoPorId(idJefe);
        if(jefe == null){
            System.out.println("Jefe no encontrado.");
            return;
        }
        jefe.agregarSubordinado(unEmpleado);
    }

    if(unaControladora.agregarEmpleado(unEmpleado)){
        System.out.println("Empleado agregado!!");
    }else{
        System.out.println("Ocurrió un Error al agregar!!");
    }
}
public static void ModificarEmpleado(){
    Controladora unaControladora = new Controladora();

    System.out.println("Modificar un Empleado");
    for (Empleado unEmp : unaControladora.ListaEmpleados()) {
        System.out.println(unEmp);
    }
    System.out.println("");
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Ingrese el ID del empleado:");
    int id = keyboard.nextInt();

    Empleado unEmpleado = unaControladora.buscarEmpleadoPorId(id);

    if(unEmpleado == null){
        System.out.println("No existe un empleado con ese ID");
        return;
    }

    System.out.println("CARGOS DISPONIBLES:");

    for (Cargo cargo : unaControladora.ListaCargos()) {
        System.out.println(cargo);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del nuevo cargo:");
    int idCargo = keyboard.nextInt();

    Cargo cargo = unaControladora.buscarCargoPorId(idCargo);

    if(cargo == null){
        System.out.println("Cargo no encontrado");
        return;
    }

    System.out.println("DEPARTAMENTOS DISPONIBLES:");
    for (Departamento dep : unaControladora.ListaDepartamentos()) {
        System.out.println(dep);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del nuevo departamento:");
    int idDepartamento = keyboard.nextInt();

    Departamento departamento = unaControladora.buscarDepartamentoPorId(idDepartamento);

    if(departamento == null){
        System.out.println("Departamento no encontrado");
        return;
    }

    System.out.println("Ingrese el nuevo salario:");
    double salario = keyboard.nextDouble();

    if(unaControladora.modificarEmpleado(id,cargo,departamento,salario)){
        System.out.println("Empleado modificado correctamente");
    }
    else{
        System.out.println("Error al modificar empleado");
    }
}
public static void EliminarEmpleado(){
    System.out.println("Eliminar un Empleado");
    Controladora unaControladora = new Controladora();

    for (Empleado unEmp: unaControladora.ListaEmpleados()) {
        System.out.println(unEmp);
    }
    System.out.println("");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();

    Empleado unEmpleado = unaControladora.buscarEmpleadoPorId(id);

    if(unEmpleado == null){
        System.out.println("No existe un Empleado con ese ID");
        return;
    }

    if(unaControladora.eliminarEmpleado(id)){
        System.out.println("Empleado eliminado.");
    }else{
        System.out.println("No puede eliminarse porque tiene subordinados.");
    }
}
public static void MostrarArbol(){
    Controladora unaControladora = new Controladora();
    unaControladora.MostrarArbol();
    Pausa();
}
public static void MoverEmpleado() {
    System.out.println("Mover un Empleado");
    Controladora unaControladora = new Controladora();
    unaControladora.MostrarArbol();
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Ingrese el ID del empleado a mover:");
    int idEmpleado = keyboard.nextInt();

    System.out.println("JEFES DISPONIBLES:");
    for (Empleado unEmp : unaControladora.ListaEmpleados()) {

        System.out.println(
                "ID: " + unEmp.getId() +
                        " | " + unEmp.getNombre() + " " + unEmp.getApellido() +
                        " | Cargo: " + unEmp.getCargo().getNombre()
        );
    }
    System.out.println("Ingrese el ID del nuevo jefe:");
    int idNuevoJefe = keyboard.nextInt();

    if (unaControladora.moverEmpleado(idEmpleado, idNuevoJefe)) {
        System.out.println("Empleado movido correctamente.");
    } else {
        System.out.println("No se pudo mover el empleado.");
    }
}
public static void BuscarEmpleadoPorId(){
    System.out.println("Buscar Empleado por Legajo");

    Controladora unaControladora = new Controladora();

    Scanner keyboard = new Scanner(System.in);

    System.out.println();
    System.out.println("Ingrese el ID del empleado:");

    int id = keyboard.nextInt();

    Empleado unEmpleado = unaControladora.buscarEmpleadoPorId(id);

    if(unEmpleado != null){
        System.out.println();
        System.out.println("===== EMPLEADO ENCONTRADO =====");
        System.out.println("ID: " + unEmpleado.getId());
        System.out.println("Nombre: " + unEmpleado.getNombre());
        System.out.println("Apellido: " + unEmpleado.getApellido());
        System.out.println("Cargo: " + unEmpleado.getCargo().getNombre());
        System.out.println("Departamento: " + unEmpleado.getDepartamento().getNombre());
        System.out.println("Salario: $" + unEmpleado.getSalario());
        System.out.println("Fecha de ingreso: " + unEmpleado.getFechaIngreso());
        System.out.println("Nivel: " + unEmpleado.getNivel());
        System.out.println("Subordinados totales: " + unEmpleado.contarSubordinados());
        System.out.println();
        System.out.println("Masa salarial del área: $" + unEmpleado.sumarMasaSalarial());
        System.out.println();
        System.out.println("CADENA DE MANDO:");
        System.out.println(unEmpleado.cadenaDeMando());
    }else{
        System.out.println("No existe un empleado con ese ID.");
    }
    Pausa();
}
public static void BuscarEmpleadoPorApellido(){
    System.out.println("Buscar Empleado por Apellido");

    Controladora unaControladora = new Controladora();

    Scanner keyboard = new Scanner(System.in);

    System.out.println("Ingrese el apellido:");
    String apellido = keyboard.nextLine();

    List<Empleado> resultados = unaControladora.buscarEmpleadoPorApellido(apellido);

    if(resultados.isEmpty()){
        System.out.println("No se encontraron empleados con ese apellido.");
    }else{
        System.out.println();
        System.out.println("===== EMPLEADOS ENCONTRADOS =====");

        for(Empleado unEmpleado : resultados){
            System.out.println("-----------------------------------");
            System.out.println("Id: " + unEmpleado.getId());
            System.out.println("Nombre: " + unEmpleado.getNombre());
            System.out.println("Apellido: " + unEmpleado.getApellido());
            System.out.println("Cargo: " + unEmpleado.getCargo().getNombre());
            System.out.println("Departamento: " + unEmpleado.getDepartamento().getNombre());
            System.out.println("Salario: $" + unEmpleado.getSalario());
            System.out.println("Fecha de ingreso: " + unEmpleado.getFechaIngreso());
            System.out.println("Nivel: " + unEmpleado.getNivel());
            System.out.println("Subordinados totales: " + unEmpleado.contarSubordinados());

            System.out.println("Cadena de mando:");
            System.out.println(unEmpleado.cadenaDeMando());
        }
    }

    Pausa();
}
public static void EmpleadosPorNivel(){
    System.out.println("Empleados por Nivel");

    Controladora unaControladora = new Controladora();

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el nivel:");
    int nivel = keyboard.nextInt();

    if(!unaControladora.existeNivel(nivel)){
        System.out.println("No hay empleados en ese nivel.");
        Pausa();
        return;
    }

    System.out.println();
    System.out.println("===== EMPLEADOS DEL NIVEL " + nivel + " =====");
    unaControladora.recorridoPorNiveles(nivel);

    Pausa();
}
//============================================
//||     FIN DE SECTOR E M P L E A D O S    ||
//============================================

//======================================
//||        SECTOR C A R G O S        ||
//======================================
public static void MenuCargos(){
    String opcion = "";
    while(!opcion.equals("0")){
        System.out.println("");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   CARGOS                                   ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║   1- Agregar Cargo                         ║");
        System.out.println("║   2- Modificar Cargo                       ║");
        System.out.println("║   3- Eliminar Cargo                        ║");
        System.out.println("║   4- Ver Árbol de Cargos                   ║");
        System.out.println("║   5- Mover Cargo                           ║");
        System.out.println("║   6- Buscar Cargo por ID                   ║");
        System.out.println("║   7- Buscar Cargo por Nombr                ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║   0- Volver                                ║");
        System.out.println("╚════════════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
            case "1":
                AgregarCargo();
                break;
//            case "2":
//                ModificarCargo();
//                break;
//            case "3":
//                EliminarCargo();
//                break;
//            case "4":
//                ListarCargos();
//                break;
//            case "5":
//                MoverCargo();
//                break;
//            case "6":
//                BuscarCargoPorId();
//                break;
//            case "7":
//                BuscarCargoPorNombre();
//                break;
//            case "8":
//                ReporteInventarioPorRama();
//                break;
        }
    }
}
public static void AgregarCargo(){
    System.out.println("Ingresar un Cargo");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();

    keyboard.nextLine();
    System.out.println("Ingrese el Nombre: ");
    String nombre = keyboard.nextLine();

    System.out.println("Ingrese el Nivel Jerárquico: ");
    int nivelJerarquico = keyboard.nextInt();

    System.out.println("Ingrese el Salario Base: ");
    Double salarioBase = keyboard.nextDouble();

    Cargo unCargo = new Cargo(id, nombre, nivelJerarquico, salarioBase);

    Controladora unaControladora = new Controladora();

    if(unaControladora.agregarCargo(unCargo)){
        System.out.println("Cargo agregado!!");
    }else{
        System.out.println("Ocurrió un error al agregar!!");
    }
}

//======================================
//||     FIN DE SECTOR C A R G O S    ||
//======================================
//region Departamentos
public static void MenuDepartamentos(){
    String opcion = "";
    while(!opcion.equals("0")){
        System.out.println("");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Departamentos                            ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║   1- Agregar Departamento                  ║");
        System.out.println("║   2- Modificar Departamento                ║");
        System.out.println("║   3- Eliminar Departamento                 ║");
        System.out.println("║   4- Ver Árbol de Departamentos            ║");
        System.out.println("║   5- Mover Departamento                    ║");
        System.out.println("║   6- Buscar Departamento por ID            ║");
        System.out.println("║   7- Buscar Departamento por Nombre        ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║   0- Volver                                ║");
        System.out.println("╚════════════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
//            case "1":
//                AgregarDepartamento();
//                break;
//            case "2":
//                ModificarDepartamento();
//                break;
//            case "3":
//                EliminarDepartamento();
//                break;
//            case "4":
//                ListarDepartamentos();
//                break;
//            case "5":
//                MoverDepartamento();
//                break;
//            case "6":
//                BuscarDepartamentoPorId();
//                break;
//            case "7":
//                BuscarDepartamentoPorNombre();
//                break;
//            case "8":
//                ReporteInventarioPorRama();
//                break;
        }
    }
}

//#region Cargos

//region Proyectos
public static void MenuProyectos(){
    String opcion = "";
    while(!opcion.equals("0")){
        System.out.println("");
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Proyectos                            ║");
        System.out.println("╠══════════════════════════════ ═════════╣");
        System.out.println("║   1- Agregar Proyecto                  ║");
        System.out.println("║   2- Modificar Proyecto                ║");
        System.out.println("║   3- Eliminar Proyecto                 ║");
        System.out.println("║   4- Ver Árbol de Proyectos            ║");
        System.out.println("║   5- Mover Proyecto                    ║");
        System.out.println("║   6- Buscar Proyecto por ID            ║");
        System.out.println("║   7- Buscar Proyecto por Nombre        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   0- Volver                            ║");
        System.out.println("╚════════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
//            case "1":
//                AgregarProyecto();
//                break;
//            case "2":
//                ModificarProyecto();
//                break;
//            case "3":
//                EliminarProyecto();
//                break;
//            case "4":
//                ListarProyectos();
//                break;
//            case "5":
//                MoverProyecto();
//                break;
//            case "6":
//                BuscarProyectoPorId();
//                break;
//            case "7":
//                BuscarProyectoPorNombre();
//                break;
//            case "8":
//                ReporteInventarioPorRama();
//                break;
        }
    }
}

//#region Proyectos

//region Asignaciones
public static void MenuAsignaciones(){
    String opcion = "";
    while(!opcion.equals("0")){
        System.out.println("");
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   Asignaciones                        ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║   1- Agregar Asignacion               ║");
        System.out.println("║   2- Modificar Asignacion             ║");
        System.out.println("║   3- Eliminar Asignacion              ║");
        System.out.println("║   4- Ver Árbol de Asignaciones        ║");
        System.out.println("║   5- Mover Asignacion                 ║");
        System.out.println("║   6- Buscar Asignacion por ID         ║");
        System.out.println("║   7- Buscar Asignacion por Nombre     ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║   0- Volver                           ║");
        System.out.println("╚═══════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
//            case "1":
//                AgregarAsignacion();
//                break;
//            case "2":
//                ModificarAsignacion();
//                break;
//            case "3":
//                EliminarAsignacion();
//                break;
//            case "4":
//                ListarAsignaciones();
//                break;
//            case "5":
//                MoverAsignacion();
//                break;
//            case "6":
//                BuscarAsignacionPorId();
//                break;
//            case "7":
//                BuscarAsignacionPorNombre();
//                break;
//            case "8":
//                ReporteInventarioPorRama();
//                break;
        }
    }
}

//#region Asignaciones