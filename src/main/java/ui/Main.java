package ui;

import model.*;
import data.AgenciaService;
import data.LectorArchivo;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Punto de entrada de la aplicación que despliega el menú administrativo a través de JOptionPane.
 * @author Gabriel
 */
public class Main {
    public static void main(String[] args) {
        String archivoFuente = "empleados.txt";
        ArrayList<Empleado> datosCargados = LectorArchivo.cargarEmpleados(archivoFuente);

        AgenciaService servicio = new AgenciaService(datosCargados);
        cargarDatosPrueba(servicio);

        String[] opcionesMenu = {
                "1. Mostrar Reporte",
                "2. Registrar Guía Turístico",
                "3. Registrar Vehículo",
                "4. Registrar Colaborador Externo",
                "5. Salir del Sistema"
        };

        boolean mantenerEjecucion = true;
        while (mantenerEjecucion) {
            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione una acción de gestión operativa:",
                    "Menú Principal - Llanquihue Tour",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesMenu,
                    opcionesMenu[0]
            );

            if (seleccion == null || seleccion.startsWith("5")) {
                mantenerEjecucion = false;
                JOptionPane.showMessageDialog(null, "Finalizando la sesión del sistema administrativo.", "Salida", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            switch (seleccion.charAt(0)) {
                case '1':
                    String reporte = servicio.generarReporteRecursos();
                    JOptionPane.showMessageDialog(null, reporte, "Reporte General de Recursos", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case '2':
                    registrarGuia(servicio);
                    break;
                case '3':
                    registrarVehiculo(servicio);
                    break;
                case '4':
                    registrarColaborador(servicio);
                    break;
            }
        }
    }

    private static void registrarGuia(AgenciaService servicio) {
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese nombre del guía:");
            if (nombre == null || nombre.trim().isEmpty()) return;
            String apellido = JOptionPane.showInputDialog("Ingrese apellido:");
            String rut = JOptionPane.showInputDialog("Ingrese RUT:");
            String especialidad = JOptionPane.showInputDialog("Ingrese especialidad:");
            String idiomas = JOptionPane.showInputDialog("Ingrese idiomas:");
            int experiencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese años de experiencia:"));

            Direccion dir = new Direccion("Los Lagos", "Puerto Varas", "Urbano", "Del Salvador", "250");
            GuiaTuristico guia = new GuiaTuristico(nombre, apellido, dir, "955556666", "guia.registro@llanquihue.cl", rut, especialidad, idiomas, experiencia);

            servicio.agregarRecurso(guia);
            JOptionPane.showMessageDialog(null, "Guía Turístico registrado de forma correcta.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el ingreso de parámetros numéricos.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void registrarVehiculo(AgenciaService servicio) {
        try {
            String patente = JOptionPane.showInputDialog("Ingrese patente (Formato ABCD-12):");
            if (patente == null || patente.trim().isEmpty()) return;
            String modelo = JOptionPane.showInputDialog("Ingrese marca y modelo del vehículo:");
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese capacidad máxima de pasajeros:"));

            Vehiculo vehiculo = new Vehiculo(patente, modelo, capacidad);
            servicio.agregarRecurso(vehiculo);
            JOptionPane.showMessageDialog(null, "Vehículo integrado al inventario operacional.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en los valores de capacidad ingresados.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void registrarColaborador(AgenciaService servicio) {
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese nombre del colaborador:");
            if (nombre == null || nombre.trim().isEmpty()) return;
            String apellido = JOptionPane.showInputDialog("Ingrese apellido:");
            String rut = JOptionPane.showInputDialog("Ingrese RUT:");
            String empresa = JOptionPane.showInputDialog("Ingrese nombre de la empresa proveedora:");
            String servicioPrestado = JOptionPane.showInputDialog("Ingrese tipo de servicio prestado:");

            Direccion dir = new Direccion("Los Lagos", "Llanquihue", "Costanera", "Vicente Pérez Rosales", "400");
            ColaboradorExterno colab = new ColaboradorExterno(nombre, apellido, dir, "944443333", "contacto.empresa@proveedor.cl", rut, empresa, servicioPrestado);

            servicio.agregarRecurso(colab);
            JOptionPane.showMessageDialog(null, "Colaborador Externo vinculado exitosamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el proceso de registro del colaborador.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void cargarDatosPrueba(AgenciaService servicio) {
        Direccion dirBase = new Direccion("Los Lagos", "Puerto Varas", "Centro", "San Pedro", "45");
        servicio.agregarRecurso(new GuiaTuristico("Ana", "Torres", dirBase, "977778888", "atorres@llanquihue.cl", "18.456.789-0", "Trekking y Senderismo", "Español, Inglés", 4));
        servicio.agregarRecurso(new Vehiculo("HGTR-45", "Mercedes-Benz Sprinter", 18));
        servicio.agregarRecurso(new ColaboradorExterno("Luis", "Morales", dirBase, "966662222", "lmorales@catamaranes.cl", "14.222.333-4", "Navegación Lago S.A.", "Transporte Lacustre"));
    }
}