package util;

import model.Direccion;
import model.Empleado;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase de utilidad encargada de la lectura y procesamiento de archivos de texto.
 * Permite cargar datos externos en colecciones dinámicas (ArrayList).
 * @author Gabriel
 */
public class LectorArchivo {

    /**
     * Lee un archivo plano (.txt o .csv) y retorna una colección dinámica de empleados.
     * @param rutaArchivo Ruta física o lógica del origen de datos.
     * @return ArrayList con los objetos Empleado correctamente instanciados.
     */
    public static ArrayList<Empleado> cargarEmpleados(String rutaArchivo) {
        ArrayList<Empleado> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length >= 13) {

                    Direccion dir = new Direccion(
                            datos[2].trim(),
                            datos[3].trim(),
                            datos[4].trim(),
                            datos[5].trim(),
                            datos[6].trim()
                    );

                    Empleado emp = new Empleado(
                            datos[0].trim(),
                            datos[1].trim(),
                            dir,
                            datos[7].trim(),
                            datos[8].trim(),
                            datos[9].trim(),
                            datos[10].trim(),
                            datos[11].trim(),
                            datos[12].trim()
                    );

                    lista.add(emp);
                }
            }
        } catch (IOException e) {
            System.out.println(" Error crítico de I/O al acceder al archivo: " + e.getMessage());
        }

        return lista;
    }
}