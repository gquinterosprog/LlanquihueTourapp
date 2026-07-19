package ui;

import data.GestorEntidades;
import javax.swing.JOptionPane;
import model.Registrable;

public class Main {
    private static final String ARCHIVO_DATOS = "datos.txt";

    public static void main(String[] args) {
        GestorEntidades gestor = new GestorEntidades(ARCHIVO_DATOS);
        FormularioEntidades formulario = new FormularioEntidades();
        ejecutarMenu(gestor, formulario);
    }

    private static void ejecutarMenu(GestorEntidades gestor, FormularioEntidades formulario) {
        String[] opciones = {
                "Registrar guía turístico",
                "Registrar vehículo",
                "Registrar colaborador externo",
                "Registrar empleado",
                "Registrar cliente",
                "Registrar proveedor",
                "Mostrar entidades registradas",
                "Buscar entidades",
                "Mostrar estadísticas",
                "Salir"
        };

        boolean ejecutando = true;
        while (ejecutando) {
            String opcion = (String) JOptionPane.showInputDialog(
                    null,
                    "Entidades cargadas: " + gestor.obtenerCantidadRecursos() + "\nSeleccione una opción:",
                    "Llanquihue Tour - Gestión de entidades",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (opcion == null || opcion.equals("Salir")) {
                ejecutando = false;
                continue;
            }

            if (opcion.equals("Registrar guía turístico")) {
                registrar(gestor, formulario.crearGuiaTuristico());
            } else if (opcion.equals("Registrar vehículo")) {
                registrar(gestor, formulario.crearVehiculo());
            } else if (opcion.equals("Registrar colaborador externo")) {
                registrar(gestor, formulario.crearColaboradorExterno());
            } else if (opcion.equals("Registrar empleado")) {
                registrar(gestor, formulario.crearEmpleado());
            } else if (opcion.equals("Registrar cliente")) {
                registrar(gestor, formulario.crearCliente());
            } else if (opcion.equals("Registrar proveedor")) {
                registrar(gestor, formulario.crearProveedor());
            } else if (opcion.equals("Mostrar entidades registradas")) {
                mostrarTexto("Entidades registradas", gestor.generarReporteRecursos());
            } else if (opcion.equals("Buscar entidades")) {
                buscar(gestor);
            } else if (opcion.equals("Mostrar estadísticas")) {
                mostrarTexto("Estadísticas", gestor.generarEstadisticasPorTipo());
            } else {
                JOptionPane.showMessageDialog(null, "Opción no reconocida.");
            }
        }

        JOptionPane.showMessageDialog(null,
                "Sesión finalizada. Los registros permanecen guardados en " + ARCHIVO_DATOS + ".",
                "Llanquihue Tour", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void registrar(GestorEntidades gestor, Registrable recurso) {
        if (recurso == null) return;
        String resultado = gestor.registrarRecurso(recurso);
        int tipoMensaje;
        if (resultado.startsWith("Entidad")) {
            tipoMensaje = JOptionPane.INFORMATION_MESSAGE;
        } else {
            tipoMensaje = JOptionPane.WARNING_MESSAGE;
        }
        JOptionPane.showMessageDialog(null, resultado, "Registro", tipoMensaje);
    }

    private static void buscar(GestorEntidades gestor) {
        String criterio = JOptionPane.showInputDialog(null,
                "Ingrese nombre, RUT, patente, empresa, servicio u otro dato:",
                "Buscar entidades", JOptionPane.QUESTION_MESSAGE);
        if (criterio != null && !criterio.trim().isEmpty()) {
            mostrarTexto("Resultados de búsqueda", gestor.buscarRecursos(criterio));
        }
    }

    private static void mostrarTexto(String titulo, String contenido) {
        JOptionPane.showMessageDialog(null, contenido, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
