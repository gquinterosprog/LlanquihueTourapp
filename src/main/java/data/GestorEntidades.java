package data;

import java.util.ArrayList;
import java.util.List;
import model.ColaboradorExterno;
import model.Empleado;
import model.GuiaTuristico;
import model.Registrable;
import model.Vehiculo;

/** Administra las entidades de Llanquihue Tour en una colección polimórfica. */
public class GestorEntidades {
    private final List<Registrable> recursos = new ArrayList<>();

    public void agregarRecurso(Registrable recurso) {
        if (recurso != null) {
            recursos.add(recurso);
        }
    }

    public String generarReporteRecursos() {
        if (recursos.isEmpty()) {
            return "No hay entidades registradas. Use el menú para ingresar una.";
        }

        StringBuilder reporte = new StringBuilder("ENTIDADES REGISTRADAS - LLANQUIHUE TOUR\n\n");
        for (Registrable recurso : recursos) {
            reporte.append(recurso.mostrarResumen()).append('\n');

            if (recurso instanceof GuiaTuristico guia) {
                reporte.append("  Acción: preparar equipamiento para ")
                        .append(guia.getEspecialidad()).append(".\n");
            } else if (recurso instanceof Vehiculo vehiculo) {
                reporte.append("  Acción: verificar vehículo para ")
                        .append(vehiculo.getCapacidadPasajeros()).append(" pasajeros.\n");
            } else if (recurso instanceof ColaboradorExterno colaborador) {
                reporte.append("  Acción: validar servicio de ")
                        .append(colaborador.getEmpresaOrigen()).append(".\n");
            } else if (recurso instanceof Empleado empleado) {
                reporte.append("  Acción: revisar jornada del área ")
                        .append(empleado.getArea()).append(".\n");
            }
            reporte.append("----------------------------------------\n");
        }
        return reporte.toString();
    }

    public List<Registrable> getRecursos() {
        return List.copyOf(recursos);
    }
}
