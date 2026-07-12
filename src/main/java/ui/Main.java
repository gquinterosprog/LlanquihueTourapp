package ui;

import data.GestorEntidades;
import data.LectorArchivo;
import data.Validador;
import javax.swing.JOptionPane;
import model.ColaboradorExterno;
import model.Direccion;
import model.GuiaTuristico;
import model.Vehiculo;

/** Punto de entrada, interfaz gráfica y persistencia de Llanquihue Tour. */
public class Main {
    private static final String ARCHIVO_DATOS = "datos.txt";
    private static final GestorEntidades GESTOR = new GestorEntidades();

    public static void main(String[] args) {
        cargarDatosGuardados();

        String[] opciones = {
                "Registrar guía turístico",
                "Registrar vehículo",
                "Registrar colaborador externo",
                "Mostrar entidades registradas",
                "Salir"
        };

        boolean ejecutando = true;
        while (ejecutando) {
            String opcion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción:",
                    "Llanquihue Tour - Gestión de entidades",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (opcion == null || opcion.equals("Salir")) {
                ejecutando = false;
            } else if (opcion.equals("Registrar guía turístico")) {
                registrarGuia();
            } else if (opcion.equals("Registrar vehículo")) {
                registrarVehiculo();
            } else if (opcion.equals("Registrar colaborador externo")) {
                registrarColaborador();
            } else if (opcion.equals("Mostrar entidades registradas")) {
                JOptionPane.showMessageDialog(null, GESTOR.generarReporteRecursos(),
                        "Reporte de entidades", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Sesión finalizada. Los registros permanecen guardados en " + ARCHIVO_DATOS + ".",
                "Llanquihue Tour", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void cargarDatosGuardados() {
        LectorArchivo.cargarDatos(ARCHIVO_DATOS).forEach(GESTOR::agregarRecurso);
    }

    private static void registrarGuia() {
        String nombre = pedirNombre("Ingrese el nombre del guía:");
        if (nombre == null) return;
        String apellido = pedirNombre("Ingrese el apellido del guía:");
        if (apellido == null) return;
        String rut = pedirTexto("Ingrese el RUT:");
        if (rut == null) return;
        String especialidad = pedirTexto("Ingrese la especialidad:");
        if (especialidad == null) return;
        String idiomas = pedirTexto("Ingrese los idiomas:");
        if (idiomas == null) return;
        Integer experiencia = pedirEnteroNoNegativo("Ingrese años de experiencia:");
        if (experiencia == null) return;

        Direccion direccion = new Direccion("Los Lagos", "Puerto Varas", "Centro", "Del Salvador", "250");
        GESTOR.agregarRecurso(new GuiaTuristico(nombre, apellido, direccion, "Sin teléfono",
                "Sin correo", rut, especialidad, idiomas, experiencia));
        LectorArchivo.guardarRegistro(ARCHIVO_DATOS,
                "GUIA;" + nombre + ";" + apellido + ";" + rut + ";" + especialidad + ";" + idiomas + ";" + experiencia);
        mostrarConfirmacion("Guía turístico registrado y guardado correctamente.");
    }

    private static void registrarVehiculo() {
        String patente = pedirTexto("Ingrese la patente:");
        if (patente == null) return;
        String modelo = pedirTexto("Ingrese marca y modelo:");
        if (modelo == null) return;
        Integer capacidad = pedirEnteroPositivo("Ingrese capacidad de pasajeros:");
        if (capacidad == null) return;

        GESTOR.agregarRecurso(new Vehiculo(patente, modelo, capacidad));
        LectorArchivo.guardarRegistro(ARCHIVO_DATOS, "VEHICULO;" + patente + ";" + modelo + ";" + capacidad);
        mostrarConfirmacion("Vehículo registrado y guardado correctamente.");
    }

    private static void registrarColaborador() {
        String nombre = pedirNombre("Ingrese el nombre del colaborador:");
        if (nombre == null) return;
        String apellido = pedirNombre("Ingrese el apellido del colaborador:");
        if (apellido == null) return;
        String rut = pedirTexto("Ingrese el RUT:");
        if (rut == null) return;
        String empresa = pedirTexto("Ingrese la empresa de origen:");
        if (empresa == null) return;
        String servicio = pedirTexto("Ingrese el servicio prestado:");
        if (servicio == null) return;

        Direccion direccion = new Direccion("Los Lagos", "Llanquihue", "Centro", "Costanera", "400");
        GESTOR.agregarRecurso(new ColaboradorExterno(nombre, apellido, direccion, "Sin teléfono",
                "Sin correo", rut, empresa, servicio));
        LectorArchivo.guardarRegistro(ARCHIVO_DATOS,
                "COLABORADOR;" + nombre + ";" + apellido + ";" + rut + ";" + empresa + ";" + servicio);
        mostrarConfirmacion("Colaborador externo registrado y guardado correctamente.");
    }

    private static String pedirNombre(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje);
            if (texto == null) return null;
            if (Validador.esSoloLetras(texto)) return texto.trim();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras y espacios.", "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String pedirTexto(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje);
            if (texto == null) return null;
            if (texto.contains(";")) {
                JOptionPane.showMessageDialog(null, "No use punto y coma (;), porque se utiliza para guardar los datos.", "Validación", JOptionPane.ERROR_MESSAGE);
            } else if (!texto.isBlank()) {
                return texto.trim();
            } else {
                JOptionPane.showMessageDialog(null, "El campo es obligatorio.", "Validación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static Integer pedirEnteroNoNegativo(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje);
            if (texto == null) return null;
            try {
                int numero = Integer.parseInt(texto.trim());
                if (numero >= 0) return numero;
            } catch (NumberFormatException ignored) {
                // Se muestra el mensaje de validación inferior.
            }
            JOptionPane.showMessageDialog(null, "Ingrese un número entero igual o mayor que cero.", "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Integer pedirEnteroPositivo(String mensaje) {
        while (true) {
            Integer numero = pedirEnteroNoNegativo(mensaje);
            if (numero == null || numero > 0) return numero;
            JOptionPane.showMessageDialog(null, "El número debe ser mayor que cero.", "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void mostrarConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Llanquihue Tour", JOptionPane.INFORMATION_MESSAGE);
    }
}
