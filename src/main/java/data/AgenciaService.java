package data;

import model.*;
import java.util.ArrayList;

/**
 * Clase de servicio que gestiona la lógica de negocio y los recursos de la agencia.
 * Administra una colección polimórfica de elementos registrables.
 * @author Gabriel
 */
public class AgenciaService {

    private ArrayList<Registrable> recursos;

    public AgenciaService() {
        this.recursos = new ArrayList<>();
    }

    public AgenciaService(ArrayList<Empleado> empleados) {
        this.recursos = new ArrayList<>();
        if (empleados != null) {
            this.recursos.addAll(empleados);
        }
    }

    public void agregarRecurso(Registrable recurso) {
        if (recurso != null) {
            this.recursos.add(recurso);
        }
    }

    public String generarReporteRecursos() {
        if (recursos == null || recursos.isEmpty()) {
            return "No hay recursos registrados en el sistema Llanquihue Tour.";
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("=========================================================\n");
        reporte.append("        INVENTARIO DE RECURSOS - LLANQUIHUE TOUR         \n");
        reporte.append("=========================================================\n\n");

        for (Registrable rec : recursos) {
            reporte.append(rec.mostrarResumen()).append("\n");

            if (rec instanceof GuiaTuristico) {
                GuiaTuristico guia = (GuiaTuristico) rec;
                reporte.append(" -> Acción Operativa: Asignar equipamiento de alta montaña para especialidad en ").append(guia.getEspecialidad()).append(".\n");
            } else if (rec instanceof Vehiculo) {
                Vehiculo vehiculo = (Vehiculo) rec;
                reporte.append(" -> Acción Operativa: Control de revisión técnica para capacidad de ").append(vehiculo.getCapacidadPasajeros()).append(" pasajeros.\n");
            } else if (rec instanceof ColaboradorExterno) {
                ColaboradorExterno colab = (ColaboradorExterno) rec;
                reporte.append(" -> Acción Operativa: Validar contrato de prestación de servicios con la empresa ").append(colab.getEmpresaOrigen()).append(".\n");
            } else if (rec instanceof Empleado) {
                reporte.append(" -> Acción Operativa: Monitoreo de asistencia y jornada laboral de personal interno.\n");
            }
            reporte.append("---------------------------------------------------------\n");
        }

        return reporte.toString();
    }

    public String filtrarPorArea(String area) {
        if (recursos == null || recursos.isEmpty()) {
            return "No hay recursos registrados en el sistema para filtrar.";
        }

        StringBuilder resultado = new StringBuilder();
        boolean encontrado = false;

        for (Registrable rec : recursos) {
            if (rec instanceof Empleado) {
                Empleado emp = (Empleado) rec;
                if (emp.getArea().equalsIgnoreCase(area.trim())) {
                    resultado.append(emp.mostrarResumen()).append("\n");
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            return "No se encontraron colaboradores internos en el área: " + area;
        }

        return resultado.toString();
    }

    public ArrayList<Registrable> getRecursos() { return recursos; }
}