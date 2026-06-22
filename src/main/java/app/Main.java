package app;

import model.Empleado;
import service.AgenciaService;
import util.LectorArchivo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String archivoFuente = "empleados.txt";
        ArrayList<Empleado> datosCargados = LectorArchivo.cargarEmpleados(archivoFuente);
        AgenciaService servicio = new AgenciaService(datosCargados);

        Scanner entrada = new Scanner(System.in);
        int selector = 0;

        do {
            System.out.println("\n==================================================");
            System.out.println("   SISTEMA CORPORATIVO: LLANQUIHUE TOUR (S5)      ");
            System.out.println("==================================================");
            System.out.println("1. Listar totalidad de Colaboradores de la Red");
            System.out.println("2. Buscar / Filtrar Colaboradores por Área");
            System.out.println("3. Terminar Ejecución");
            System.out.print("Ingrese Operación: ");

            try {
                selector = Integer.parseInt(entrada.nextLine());

                switch (selector) {
                    case 1:
                        System.out.println("\n --- DESPLIEGUE COMPLETO DE REGISTROS ---");
                        servicio.mostrarTodos();
                        break;
                    case 2:
                        System.out.print("\nEscriba el Área (ej: Operaciones, Turismo, Administracion): ");
                        String areaFiltro = entrada.nextLine();
                        System.out.println("\n --- COINCIDENCIAS DETECTADAS ---");
                        servicio.filtrarPorArea(areaFiltro);
                        break;
                    case 3:
                        System.out.println("\n Cerrando sistema... ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("️ Opción inválida. Seleccione un número del menú (1-3).");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Entrada Incorrecta: Por favor ingrese un número válido.");
            }
        } while (selector != 3);

        entrada.close();
    }
}