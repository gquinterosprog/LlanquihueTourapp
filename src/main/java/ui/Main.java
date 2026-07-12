package ui;

import model.*;
import data.AgenciaService;
import data.LectorArchivo;
import data.Validador;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String archivoFuente = "empleados.txt";
        ArrayList<Registrable> datosCargados = LectorArchivo.cargarDatos(archivoFuente);

        AgenciaService servicio = new AgenciaService(new ArrayList<>());

        for (Registrable recurso : datosCargados) {
            servicio.agregarRecurso(recurso);
        }

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
        String nombre = pedirTextoSoloLetras("Ingrese nombre del guía:");
        if (nombre == null) return;

        String apellido = pedirTextoSoloLetras("Ingrese apellido:");
        if (apellido == null) return;

        String rut = pedirTextoLibre("Ingrese RUT:");
        if (rut == null) return;

        String especialidad = pedirTextoLibre("Ingrese especialidad:");
        if (especialidad == null) return;

        String idiomas = pedirTextoLibre("Ingrese idiomas:");
        if (idiomas == null) return;

        int experiencia = pedirEntero("Ingrese años de experiencia:");
        if (experiencia == -1) return;

        Direccion dir = new Direccion("Los Lagos", "Puerto Varas", "Urbano", "Del Salvador", "250");
        GuiaTuristico guia = new GuiaTuristico(nombre, apellido, dir, "955556666", "guia.registro@llanquihue.cl", rut, especialidad, idiomas, experiencia);

        servicio.agregarRecurso(guia);

        String datosGuia = "GUIA;" + nombre + ";" + apellido + ";" + rut + ";" + especialidad + ";" + idiomas + ";" + experiencia;
        data.LectorArchivo.guardarRegistro("empleados.txt", datosGuia);

        JOptionPane.showMessageDialog(null, "Guía Turístico registrado de forma correcta.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void registrarVehiculo(AgenciaService servicio) {
        String patente = pedirTextoLibre("Ingrese patente (Formato ABCD-12):");
        if (patente == null) return;

        String modelo = pedirTextoLibre("Ingrese marca y modelo del vehículo:");
        if (modelo == null) return;

        int capacidad = pedirEntero("Ingrese capacidad máxima de pasajeros:");
        if (capacidad == -1) return;

        Vehiculo vehiculo = new Vehiculo(patente, modelo, capacidad);

        servicio.agregarRecurso(vehiculo);

        String datosVehiculo = "VEHICULO;" + patente + ";" + modelo + ";" + capacidad;
        data.LectorArchivo.guardarRegistro("empleados.txt", datosVehiculo);

        JOptionPane.showMessageDialog(null, "Vehículo integrado al inventario operacional.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void registrarColaborador(AgenciaService servicio) {
        String nombre = pedirTextoSoloLetras("Ingrese nombre del colaborador:");
        if (nombre == null) return;

        String apellido = pedirTextoSoloLetras("Ingrese apellido:");
        if (apellido == null) return;

        String rut = pedirTextoLibre("Ingrese RUT:");
        if (rut == null) return;

        String empresa = pedirTextoLibre("Ingrese nombre de la empresa proveedora:");
        if (empresa == null) return;

        String servicioPrestado = pedirTextoLibre("Ingrese tipo de servicio prestado:");
        if (servicioPrestado == null) return;

        Direccion dir = new Direccion("Los Lagos", "Llanquihue", "Costanera", "Vicente Pérez Rosales", "400");
        ColaboradorExterno colab = new ColaboradorExterno(nombre, apellido, dir, "944443333", "contacto.empresa@proveedor.cl", rut, empresa, servicioPrestado);

        servicio.agregarRecurso(colab);

        String datosColab = "COLABORADOR;" + nombre + ";" + apellido + ";" + rut + ";" + empresa + ";" + servicioPrestado;
        data.LectorArchivo.guardarRegistro("empleados.txt", datosColab);

        JOptionPane.showMessageDialog(null, "Colaborador Externo vinculado exitosamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void cargarDatosPrueba(AgenciaService servicio) {
        Direccion dirBase = new Direccion("Los Lagos", "Puerto Varas", "Centro", "San Pedro", "45");
        servicio.agregarRecurso(new GuiaTuristico("Ana", "Torres", dirBase, "977778888", "atorres@llanquihue.cl", "18.456.789-0", "Trekking y Senderismo", "Español, Inglés", 4));
        servicio.agregarRecurso(new Vehiculo("HGTR-45", "Mercedes-Benz Sprinter", 18));
        servicio.agregarRecurso(new ColaboradorExterno("Luis", "Morales", dirBase, "966662222", "lmorales@catamaranes.cl", "14.222.333-4", "Navegación Lago S.A.", "Transporte Lacustre"));
    }

    private static String pedirTextoSoloLetras(String mensaje) {
        String texto = "";
        boolean esValido = false;

        while (!esValido) {
            texto = JOptionPane.showInputDialog(mensaje);
            if (texto == null) return null;

            if (texto.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: El campo no puede estar vacío.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            } else if (!Validador.esSoloLetras(texto)) {
                JOptionPane.showMessageDialog(null, "Error: Este campo no permite números ni símbolos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } else {
                esValido = true;
            }
        }
        return texto.trim();
    }

    private static String pedirTextoLibre(String mensaje) {
        String texto = "";
        boolean esValido = false;

        while (!esValido) {
            texto = JOptionPane.showInputDialog(mensaje);
            if (texto == null) return null;

            if (texto.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: El campo no puede estar vacío.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            } else {
                esValido = true;
            }
        }
        return texto.trim();
    }

    private static int pedirEntero(String mensaje) {
        int numero = -1;
        while (numero < 0) {
            String input = JOptionPane.showInputDialog(mensaje + " (Solo números positivos):");
            if (input == null) return -1;

            try {
                numero = Integer.parseInt(input.trim());
                if (numero < 0) {
                    JOptionPane.showMessageDialog(null, "Error: El número no puede ser negativo.", "Error Numérico", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido (ej: 5). No ingrese letras.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                numero = -1;
            }
        }
        return numero;
    }
}