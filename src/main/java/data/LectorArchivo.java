package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.*;

public class LectorArchivo {


    public static ArrayList<Registrable> cargarDatos(String rutaArchivo) {
        ArrayList<Registrable> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(";");

                try {
                    switch (partes[0].toUpperCase()) {
                        case "GUIA":
                            Direccion dirGuia = new Direccion("Los Lagos", "Puerto Varas", "Urbano", "Del Salvador", "250");
                            GuiaTuristico guia = new GuiaTuristico(partes[1], partes[2], dirGuia, "955556666", "guia@llanquihue.cl", partes[3], partes[4], partes[5], Integer.parseInt(partes[6]));
                            lista.add(guia);
                            break;

                        case "VEHICULO":
                            Vehiculo vehiculo = new Vehiculo(partes[1], partes[2], Integer.parseInt(partes[3]));
                            lista.add(vehiculo);
                            break;

                        case "COLABORADOR":
                            Direccion dirColab = new Direccion("Los Lagos", "Llanquihue", "Costanera", "Vicente Pérez Rosales", "400");
                            ColaboradorExterno colab = new ColaboradorExterno(partes[1], partes[2], dirColab, "944443333", "contacto@proveedor.cl", partes[3], partes[4], partes[5]);
                            lista.add(colab);
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar la línea: " + linea + " - " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo " + rutaArchivo + ": " + e.getMessage());
        }

        return lista;
    }


    public static void guardarRegistro(String rutaArchivo, String datos) {
        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(datos);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}