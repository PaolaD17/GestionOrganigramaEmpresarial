import Dominio.*;
import java.util.*;

public static void main(String[] args) {
    String opcion = "";
    Controladora unaControladora = new Controladora();

    unaControladora.cargoDatos();

    while(!opcion.equals("0")){
        System.out.println("");
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║ Sistema de Gestión de Organigrama Empresarial ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   Empleado                                    ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   1- Agregar Empleado                         ║");
        System.out.println("║   2- Modificar Empleado                       ║");
        System.out.println("║   3- Eliminar Empleado                        ║");
        System.out.println("║   4- Listar Empleados                         ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   Departamento                                ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   5- Agregar Departamento                     ║");
        System.out.println("║   6- Modificar Departamento                   ║");
        System.out.println("║   7- Eliminar Departamento                    ║");
        System.out.println("║   8- Listar Departamentos                     ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   Cargo                                       ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   9- Agregar Cargo                            ║");
        System.out.println("║  10- Modificar Cargo                          ║");
        System.out.println("║  11- Eliminar Cargo                           ║");
        System.out.println("║  12- Listar Cargos                            ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   Proyecto                                    ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║  13- Agregar Proyecto                         ║");
        System.out.println("║  14- Modificar Proyecto                       ║");
        System.out.println("║  15- Eliminar Proyecto                        ║");
        System.out.println("║  16- Listar Proyectos                         ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   Asignación                                  ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║  17- Agregar Asignación                       ║");
        System.out.println("║  18- Modificar Asignación                     ║");
        System.out.println("║  19- Eliminar Asignación                      ║");
        System.out.println("║  20- Listar Asignaciones                      ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║   0- Salir                                    ║");
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
                ListarEmpleados();
                break;
            case "5":
                AgregarDepartamento();
                break;
            case "6":
                ModificarDepartamento();
                break;
            case "7":
                EliminarDepartamento();
                break;
            case "8":
                ListarDepartamentos();
                break;
            case "9":
                AgregarCargo();
                break;
            case "10":
                ModificarCargo();
                break;
            case "11":
                EliminarCargo();
                break;
            case "12":
                ListarCargos();
                break;
            case "13":
                AgregarProyecto();
                break;
            case "14":
                ModificarProyecto();
                break;
            case "15":
                EliminarProyecto();
                break;
            case "16":
                ListarProyectos();
                break;
            case "17":
                AgregarAsignacion();
                break;
            case "18":
                ModificarAsignacion();
                break;
            case "19":
                EliminarAsignacion();
                break;
            case "20":
                ListarAsignaciones();
                break;

        }
    }
}
