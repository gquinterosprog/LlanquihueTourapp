package service;

import model.Empleado;
import java.util.ArrayList;

/**
 * Clase de servicio que gestiona la lógica de negocio de la agencia.
 * Permite realizar operaciones de filtrado y visualización sobre la colección de empleados.
 * @author Gabriel
 */
public class AgenciaService {

    private ArrayList<Empleado> empleados;

    /**
     * Constructor que inicializa el servicio con una lista de empleados previamente cargada.
     * @param empleados Colección dinámica de empleados.
     */
    public AgenciaService(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    /**
     * Muestra en consola la totalidad de los colaboradores registrados.
     */
    public void mostrarTodos() {
        if (empleados == null || empleados.isEmpty()) {
            System.out.println("️ No hay empleados registrados en el sistema o el archivo está vacío.");
            return;
        }
        for (Empleado emp : empleados) {
            System.out.println(emp.toString());
        }
    }

    /**
     * Filtra y muestra los colaboradores que pertenecen a un área específica.
     * @param area Nombre del área a buscar.
     */
    public void filtrarPorArea(String area) {
        if (empleados == null || empleados.isEmpty()) {
            System.out.println(" No hay empleados registrados para filtrar.");
            return;
        }

        boolean encontrado = false;
        for (Empleado emp : empleados) {
            // Ignoramos mayúsculas/minúsculas para hacer la búsqueda más amigable
            if (emp.getArea().equalsIgnoreCase(area.trim())) {
                System.out.println(emp.toString());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(" No se encontraron colaboradores en el área: " + area);
        }
    }
}