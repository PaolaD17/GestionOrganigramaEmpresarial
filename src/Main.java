import Dominio.*;

import java.sql.SQLOutput;
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

    if(unaControladora.agregarEmpleado(unEmpleado)){
        if(idJefe != 0){
            Empleado jefe = unaControladora.buscarEmpleadoPorId(idJefe);
            if(jefe == null){
                System.out.println("Jefe no encontrado.");
                return;
            }
            jefe.agregarSubordinado(unEmpleado);
        }
        System.out.println("Empleado agregado!!");
    }
    else{
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
        System.out.println("║   4- Listado de Cargos                     ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║   0- Volver                                ║");
        System.out.println("╚════════════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
            case "1":
                AgregarCargo();
                break;
            case "2":
                ModificarCargo();
                break;
            case "3":
                EliminarCargo();
                break;
            case "4":
                ListarCargos();
                break;
        }
    }
}
public static void AgregarCargo(){
    Controladora unaControladora = new Controladora();

    System.out.println("Ingresar un Cargo");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();
    if (unaControladora.buscarCargoPorId(id) != null) {
        System.out.println("Ya existe un cargo con ese ID.");
        return;
    }

    keyboard.nextLine();
    System.out.println("Ingrese el Nombre: ");
    String nombre = keyboard.nextLine();

    System.out.println("Ingrese el Nivel Jerárquico: ");
    int nivelJerarquico = keyboard.nextInt();

    System.out.println("Ingrese el Salario Base: ");
    Double salarioBase = keyboard.nextDouble();

    Cargo unCargo = new Cargo(id, nombre, nivelJerarquico, salarioBase);

    if(unaControladora.agregarCargo(unCargo)){
        System.out.println("Cargo agregado!");
    }else{
        System.out.println("Ocurrió un error al agregar!!");
    }
}
public static void ModificarCargo(){
    Controladora unaControladora = new Controladora();

    System.out.println("Modificar un Cargo");
    for (Cargo unCargo : unaControladora.ListaCargos()) {
        System.out.println(unCargo);
    }
    System.out.println("");
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Ingrese el ID del cargo a modificar:");
    int id = keyboard.nextInt();
    keyboard.nextLine();

    Cargo unCargo = unaControladora.buscarCargoPorId(id);

    if(unCargo == null){
        System.out.println("No existe un cargo con ese ID");
        return;
    }

    System.out.println("Ingrese el nuevo nombre del cargo:");
    String nombre = keyboard.nextLine();

    System.out.println("Ingrese el nuevo nivel jerárquico:");
    int nivelJerarquico = keyboard.nextInt();

    System.out.println("Ingrese el nuevo salario base:");
    Double salarioBase = keyboard.nextDouble();

    if(unaControladora.modificarCargo(id,nombre,nivelJerarquico,salarioBase)){
        System.out.println("Cargo modificado correctamente");
    }
    else{
        System.out.println("Error al modificar cargo");
    }
}
public static void EliminarCargo(){
    System.out.println("Eliminar un Cargo");
    Controladora unaControladora = new Controladora();

    for (Cargo unCargo: unaControladora.ListaCargos()) {
        System.out.println(unCargo);
    }
    System.out.println("");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();

    Cargo unCargo = unaControladora.buscarCargoPorId(id);

    if(unCargo == null){
        System.out.println("No existe un Cargo con ese ID");
        return;
    }

    if(unaControladora.eliminarCargo(id)){
        System.out.println("Cargo eliminado.");
    }else{
        System.out.println("Error al eliminar el cargo.");
    }
}
public static void ListarCargos(){
    System.out.println("LISTADO DE CARGOS:");
    System.out.println("--------------------------");
    MostrarCargos();
    Pausa();
}
public static void MostrarCargos(){
    Controladora unaControladora = new Controladora();
    for (Cargo unCargo: unaControladora.ListaCargos()) {
        System.out.println(unCargo);
    }
}
//======================================
//||     FIN DE SECTOR C A R G O S    ||
//======================================

//==========================================
//||   SECTOR D E P A R T A M E N T O S   ||
//==========================================
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
        System.out.println("║   4- Listado de Departamentos              ║");
        System.out.println("║   5- Empleados por Departamento            ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║   0- Volver                                ║");
        System.out.println("╚════════════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
            case "1":
                AgregarDepartamento();
                break;
            case "2":
                ModificarDepartamento();
                break;
            case "3":
                EliminarDepartamento();
                break;
            case "4":
                ListarDepartamentos();
                break;
            case "5":
                EmpleadosPorDepartamento();
                break;
        }
    }
}
public static void AgregarDepartamento(){
    Controladora unaControladora = new Controladora();

    System.out.println("Ingresar un Departamento");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();
    if (unaControladora.buscarDepartamentoPorId(id) != null) {
        System.out.println("Ya existe un departamento con ese ID.");
        return;
    }

    keyboard.nextLine();
    System.out.println("Ingrese el Nombre: ");
    String nombre = keyboard.nextLine();

    System.out.println("Ingrese la Descripción: ");
    String descripcion = keyboard.nextLine();

    System.out.println("EMPLEADOS DISPONIBLES:");
    for (Empleado empleado : unaControladora.ListaEmpleados()) {
        System.out.println(empleado);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del Empleado responsable del departamento:");
    int idEmpleado = keyboard.nextInt();
    Empleado responsable = unaControladora.buscarEmpleadoPorId(idEmpleado);
    if (responsable == null) {
        System.out.println("Empleado no encontrado.");
        return;
    }

    Departamento unDep = new Departamento(id, nombre, descripcion, responsable);

    if(unaControladora.agregarDepartamento(unDep)){
        System.out.println("Departamento agregado!");
    }else{
        System.out.println("Ocurrió un error al agregar!");
    }
}
public static void ModificarDepartamento(){
    Controladora unaControladora = new Controladora();

    System.out.println("Modificar un Departamento");
    for (Departamento unDep : unaControladora.ListaDepartamentos()) {
        System.out.println(unDep);
    }
    System.out.println("");
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Ingrese el ID del Departamento a modificar:");
    int id = keyboard.nextInt();

    Departamento unDep = unaControladora.buscarDepartamentoPorId(id);

    if(unDep == null){
        System.out.println("No existe un Departamento con ese ID");
        return;
    }

    System.out.println("Ingrese el nuevo nombre:");
    String nombre = keyboard.nextLine();

    System.out.println("Ingrese la nueva Descripción:");
    String descripcion = keyboard.nextLine();

    System.out.println("EMPLEADOS DISPONIBLES:");
    for (Empleado empleado : unaControladora.ListaEmpleados()) {
        System.out.println(empleado);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del Empleado:");
    int idEmpleado = keyboard.nextInt();
    Empleado responsable = unaControladora.buscarEmpleadoPorId(idEmpleado);
    if (responsable == null) {
        System.out.println("Empleado no encontrado.");
        return;
    }

    if(unaControladora.modificarDepartamento(id,nombre,descripcion,responsable)){
        System.out.println("Departamento modificado correctamente");
    }
    else{
        System.out.println("Error al modificar departamento");
    }
}
public static void EliminarDepartamento(){
    System.out.println("Eliminar un Departamento");
    Controladora unaControladora = new Controladora();

    for (Departamento unDep: unaControladora.ListaDepartamentos()) {
        System.out.println(unDep);
    }
    System.out.println("");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();

    Departamento unDep = unaControladora.buscarDepartamentoPorId(id);

    if(unDep == null){
        System.out.println("No existe un Departamento con ese ID");
        return;
    }

    if(unaControladora.eliminarDepartamento(id)){
        System.out.println("Departamento eliminado.");
    }else{
        System.out.println("Error al eliminar el Departamento.");
    }
}
public static void ListarDepartamentos(){
    System.out.println("LISTADO DE DEPARTAMENTOS:");
    System.out.println("--------------------------");
    MostrarDepartamentos();
    Pausa();
}
public static void MostrarDepartamentos(){
    Controladora unaControladora = new Controladora();
    for (Departamento unDep: unaControladora.ListaDepartamentos()) {
        System.out.println(unDep);
    }
}
public static void EmpleadosPorDepartamento(){
    Controladora unaControladora = new Controladora();

    System.out.println("EMPLEADOS POR DEPARTAMENTO");

    System.out.println();
    System.out.println("Departamentos disponibles:");

    for(Departamento unDep : unaControladora.ListaDepartamentos()){
        System.out.println(unDep.getId() + " - " + unDep.getNombre());
    }

    Scanner keyboard = new Scanner(System.in);

    System.out.println();
    System.out.println("Ingrese el ID del departamento:");

    int idDepartamento = keyboard.nextInt();

    Departamento departamento = unaControladora.buscarDepartamentoPorId(idDepartamento);

    if(departamento == null){
        System.out.println("No existe un departamento con ese ID.");
        Pausa();
        return;
    }

    List<Empleado> empleados = unaControladora.empleadosPorDepartamento(idDepartamento);

    System.out.println();
    System.out.println("===== DEPARTAMENTO =====");
    System.out.println("Nombre: " + departamento.getNombre());

    if(departamento.getResponsable() != null){
        System.out.println("Responsable: " + departamento.getResponsable().getNombre() + " " + departamento.getResponsable().getApellido());
    }

    System.out.println();
    System.out.println("===== EMPLEADOS =====");

    for(Empleado unEmpleado : empleados){
        System.out.println(unEmpleado);
    }

    System.out.println();
    System.out.println("Cantidad total de empleados: " + empleados.size());

    Pausa();
}
//=================================================
//||   FIN DE SECTOR D E P A R T A M E N T O S   ||
//=================================================

//================================
//||   SECTOR P R O Y E C T O   ||
//================================
public static void MenuProyectos(){
    String opcion = "";
    while(!opcion.equals("0")){
        System.out.println("");
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Proyectos                            ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   1- Agregar Proyecto                  ║");
        System.out.println("║   2- Modificar Proyecto                ║");
        System.out.println("║   3- Eliminar Proyecto                 ║");
        System.out.println("║   4- Listar Proyectos                  ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║   0- Volver                            ║");
        System.out.println("╚════════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
            case "1":
                AgregarProyecto();
                break;
            case "2":
                ModificarProyecto();
                break;
            case "3":
                EliminarProyecto();
                break;
            case "4":
                ListarProyectos();
                break;
        }
    }
}
public static void AgregarProyecto(){
    Controladora unaControladora = new Controladora();

    System.out.println("Ingresar un Proyecto");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();
    if (unaControladora.buscarProyectoPorId(id) != null) {
        System.out.println("Ya existe un proyecto con ese ID.");
        return;
    }

    keyboard.nextLine();
    System.out.println("Ingrese el Nombre: ");
    String nombre = keyboard.nextLine();

    System.out.println("Ingrese la fecha de inicio (yyyy-MM-dd): ");
    LocalDate fechaInicio = LocalDate.parse(keyboard.nextLine());

    System.out.println("Ingrese la fecha de finalización (yyyy-MM-dd): ");
    LocalDate fechaFin = LocalDate.parse(keyboard.nextLine());

    System.out.println("Ingrese el Estado (planificado, en curso o finalizado): ");
    String estado = keyboard.nextLine();
    if (!estado.equalsIgnoreCase("Planificado")
            && !estado.equalsIgnoreCase("En Curso")
            && !estado.equalsIgnoreCase("Finalizado")) {
        System.out.println("Estado inválido.");
        return;
    }

    System.out.println("EMPLEADOS DISPONIBLES:");
    for (Empleado empleado : unaControladora.ListaEmpleados()) {
        System.out.println(empleado);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del Empleado responsable:");
    int idEmpleado = keyboard.nextInt();
    Empleado responsable = unaControladora.buscarEmpleadoPorId(idEmpleado);
    if (responsable == null) {
        System.out.println("Empleado no encontrado.");
        return;
    }

    Proyecto unProy = new Proyecto(id, nombre, fechaInicio, fechaFin, estado, responsable);

    if(unaControladora.agregarProyecto(unProy)){
        System.out.println("Proyecto agregado!");
    }else{
        System.out.println("Ocurrió un error al agregar!");
    }
}
public static void ModificarProyecto(){
    Controladora unaControladora = new Controladora();

    System.out.println("Modificar un Proyecto");
    for (Proyecto unProy : unaControladora.ListaProyectos()) {
        System.out.println(unProy);
    }
    System.out.println("");
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Ingrese el ID del Proyecto:");
    int id = keyboard.nextInt();
    keyboard.nextLine();

    Proyecto unProyecto = unaControladora.buscarProyectoPorId(id);

    if(unProyecto == null){
        System.out.println("No existe un proyecto con ese ID");
        return;
    }

    System.out.println("Ingrese el nuevo nombre:");
    String nombre = keyboard.nextLine();

    System.out.println("Ingrese la nueva fecha de inicio (yyyy-MM-dd): ");
    LocalDate fechaInicio = LocalDate.parse(keyboard.nextLine());

    System.out.println("Ingrese la nueva fecha de finalización (yyyy-MM-dd): ");
    LocalDate fechaFin = LocalDate.parse(keyboard.nextLine());

    System.out.println("Ingrese el nuevo estado:");
    String estado = keyboard.nextLine();

    System.out.println("EMPLEADOS DISPONIBLES:");
    for (Empleado empleado : unaControladora.ListaEmpleados()) {
        System.out.println(empleado);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del Empleado responsable:");
    int idEmpleado = keyboard.nextInt();
    Empleado responsable = unaControladora.buscarEmpleadoPorId(idEmpleado);
    if (responsable == null) {
        System.out.println("Empleado no encontrado.");
        return;
    }

    if(unaControladora.modificarProyecto(id,nombre,fechaInicio,fechaFin,estado,responsable)){
        System.out.println("Proyecto modificado correctamente");
    }
    else{
        System.out.println("Error al modificar proyecto");
    }
}
public static void EliminarProyecto(){
    System.out.println("Eliminar un Proyecto");
    Controladora unaControladora = new Controladora();

    for (Proyecto unProy: unaControladora.ListaProyectos()) {
        System.out.println(unProy);
    }
    System.out.println("");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();

    Proyecto unProy = unaControladora.buscarProyectoPorId(id);

    if(unProy == null){
        System.out.println("No existe un Proyecto con ese ID");
        return;
    }

    if(unaControladora.eliminarProyecto(id)){
        System.out.println("Proyecto eliminado.");
    }else{
        System.out.println("Error al eliminar el Proyecto.");
    }
}
public static void ListarProyectos(){
    System.out.println("LISTADO DE PROYECTOS:");
    System.out.println("--------------------------");
    MostrarProyectos();
    Pausa();
}
public static void MostrarProyectos(){
    Controladora unaControladora = new Controladora();
    for (Proyecto unProy: unaControladora.ListaProyectos()) {
        System.out.println(unProy);
    }
}
//=======================================
//||   FIN DE SECTOR P R O Y E C T O   ||
//=======================================

//====================================
//||   SECTOR A S I G N A C I Ó N   ||
//====================================
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
        System.out.println("║   4- Listar Asignaciones              ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║   0- Volver                           ║");
        System.out.println("╚═══════════════════════════════════════╝");

        Scanner keyboard = new Scanner(System.in);
        opcion = keyboard.nextLine();

        switch (opcion){
            case "1":
                AgregarAsignacion();
                break;
            case "2":
                ModificarAsignacion();
                break;
            case "3":
                EliminarAsignacion();
                break;
            case "4":
                ListarAsignaciones();
                break;
        }
    }
}
public static void AgregarAsignacion(){
    Controladora unaControladora = new Controladora();

    System.out.println("Asigne un proyecto");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();
    if (unaControladora.buscarAsignacionPorId(id) != null) {
        System.out.println("Ya existe una asignación con ese ID.");
        return;
    }

    System.out.println("EMPLEADOS DISPONIBLES:");
    for (Empleado empleado : unaControladora.ListaEmpleados()) {
        System.out.println(empleado);
    }
    System.out.println("");
    System.out.println("Seleccione el ID del empleado a asignar:");
    int idEmpleado = keyboard.nextInt();
    Empleado empleado = unaControladora.buscarEmpleadoPorId(idEmpleado);
    if (empleado == null) {
        System.out.println("Empleado no encontrado.");
        return;
    }

    System.out.println("PROYECTOS DISPONIBLES:");
    for (Proyecto proyecto : unaControladora.ListaProyectos()) {
        System.out.println(proyecto);
    }
    System.out.println("");
    System.out.println("Seleccione el ID del proyecto:");
    int idProyecto = keyboard.nextInt();
    Proyecto proyecto = unaControladora.buscarProyectoPorId(idProyecto);
    if (proyecto == null) {
        System.out.println("Proyecto no encontrado.");
        return;
    }
    keyboard.nextLine();

    System.out.println("Ingrese las horas asignadas: ");
    int horasAsignadas = keyboard.nextInt();

    System.out.println("Ingrese el rol (desarrollador, tester, etc): ");
    String rol = keyboard.nextLine();

    Asignacion unaAsig = new Asignacion(id, empleado, proyecto, horasAsignadas, rol);

    if(unaControladora.agregarAsignacion(unaAsig)){
        System.out.println("Asignación realizada!");
    }else{
        System.out.println("Ocurrió un error al realizar la asignación!");
    }
}
public static void ModificarAsignacion(){
    Controladora unaControladora = new Controladora();

    System.out.println("Modificar una Asignación");
    for (Asignacion unaAsig : unaControladora.ListaAsignaciones()) {
        System.out.println(unaAsig);
    }
    System.out.println("");
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Ingrese el ID de la Asignación:");
    int id = keyboard.nextInt();

    Asignacion unAsignacion = unaControladora.buscarAsignacionPorId(id);

    if(unAsignacion == null){
        System.out.println("No existe una asignación con ese ID");
        return;
    }

    System.out.println("EMPLEADOS DISPONIBLES:");
    for (Empleado empleado : unaControladora.ListaEmpleados()) {
        System.out.println(empleado);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del nuevo Empleado a asignar:");
    int idEmpleado = keyboard.nextInt();
    Empleado empleado = unaControladora.buscarEmpleadoPorId(idEmpleado);
    if (empleado == null) {
        System.out.println("Empleado no encontrado.");
        return;
    }

    System.out.println("PROYECTOS DISPONIBLES:");
    for (Proyecto proyecto : unaControladora.ListaProyectos()) {
        System.out.println(proyecto);
    }
    System.out.println("");
    System.out.println("Ingrese el ID del nuevo Proyecto:");
    int idProyecto = keyboard.nextInt();
    Proyecto proyecto = unaControladora.buscarProyectoPorId(idProyecto);
    if (proyecto == null) {
        System.out.println("Proyecto no encontrado.");
        return;
    }

    System.out.println("Ingrese la nueva cantidad de Horas Asignadas: ");
    int horasAsignadas = keyboard.nextInt();

    System.out.println("Ingrese el nuevo Rol");
    String rol = keyboard.nextLine();

    if(unaControladora.modificarAsignacion(id,empleado,proyecto,horasAsignadas,rol)){
        System.out.println("Asignación modificada correctamente");
    }
    else{
        System.out.println("Error al modificar asignación");
    }
}
public static void EliminarAsignacion(){
    System.out.println("Eliminar una Asignación");
    Controladora unaControladora = new Controladora();

    for (Asignacion unaAsig: unaControladora.ListaAsignaciones()) {
        System.out.println(unaAsig);
    }
    System.out.println("");

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Ingrese el ID: ");
    int id = keyboard.nextInt();

    Asignacion unaAsig = unaControladora.buscarAsignacionPorId(id);

    if(unaAsig == null){
        System.out.println("No existe una Asignación con ese ID");
        return;
    }

    if(unaControladora.eliminarAsignacion(id)){
        System.out.println("Asignacion eliminado.");
    }else{
        System.out.println("Error al eliminar la Asignacion.");
    }
}
public static void ListarAsignaciones(){
    System.out.println("LISTADO DE ASIGNACIONES:");
    System.out.println("--------------------------");
    MostrarAsignaciones();
    Pausa();
}
public static void MostrarAsignaciones(){
    Controladora unaControladora = new Controladora();
    for (Asignacion unaAsig : unaControladora.ListaAsignaciones()) {
        System.out.println(unaAsig);
    }
}
//===========================================
//||   FIN DE SECTOR A S I G N A C I Ó N   ||
//===========================================