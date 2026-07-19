package data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.ColaboradorExterno;
import model.Direccion;
import model.Empleado;
import model.GuiaTuristico;
import model.Proveedor;
import model.Registrable;
import model.Rut;
import model.Vehiculo;
import util.RutInvalidoException;

/** Lee y guarda los registros en un archivo de texto */
public final class LectorArchivo {
    private LectorArchivo() {
    }

    public static List<Registrable> cargarDatos(String rutaArchivo) {
        List<Registrable> recursos = new ArrayList<>();
        Path ruta = Path.of(rutaArchivo);

        if (!Files.exists(ruta)) {
            return recursos;
        }

        try {
            for (String linea : Files.readAllLines(ruta, StandardCharsets.UTF_8)) {
                if (linea.trim().isEmpty()) continue;
                procesarLinea(linea, recursos);
            }
        } catch (IOException e) {
            System.out.println("No fue posible leer el archivo: " + e.getMessage());
        }
        return recursos;
    }

    public static boolean guardarRegistro(String rutaArchivo, String linea) {
        if (linea == null || linea.trim().isEmpty()) return false;
        try {
            Files.writeString(Path.of(rutaArchivo), linea + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.out.println("No fue posible guardar el registro: " + e.getMessage());
            return false;
        }
    }

    private static void procesarLinea(String linea, List<Registrable> recursos) {
        String[] partes = linea.split(";", -1);
        try {
            String tipo = partes[0].trim().toUpperCase();
            if (tipo.equals("GUIA")) {
                validarCantidadCampos(partes, 14);
                recursos.add(new GuiaTuristico(partes[1], partes[2], crearDireccion(partes), partes[4],
                        partes[5], new Rut(partes[3]), partes[11], partes[12], Integer.parseInt(partes[13])));
            } else if (tipo.equals("VEHICULO")) {
                validarCantidadCampos(partes, 4);
                recursos.add(new Vehiculo(partes[1], partes[2], Integer.parseInt(partes[3])));
            } else if (tipo.equals("COLABORADOR")) {
                validarCantidadCampos(partes, 13);
                recursos.add(new ColaboradorExterno(partes[1], partes[2], crearDireccion(partes), partes[4],
                        partes[5], new Rut(partes[3]), partes[11], partes[12]));
            } else if (tipo.equals("EMPLEADO")) {
                validarCantidadCampos(partes, 14);
                recursos.add(new Empleado(partes[1], partes[2], crearDireccion(partes), partes[4],
                        partes[5], new Rut(partes[3]), partes[11], partes[12], partes[13]));
            } else if (tipo.equals("CLIENTE")) {
                validarCantidadCampos(partes, 13);
                recursos.add(new Cliente(partes[1], partes[2], crearDireccion(partes), partes[4],
                        partes[5], new Rut(partes[3]), partes[11], partes[12]));
            } else if (tipo.equals("PROVEEDOR")) {
                validarCantidadCampos(partes, 13);
                recursos.add(new Proveedor(partes[1], partes[2], crearDireccion(partes), partes[4],
                        partes[5], new Rut(partes[3]), partes[11], partes[12]));
            } else {
                System.out.println("Tipo de registro desconocido: " + partes[0]);
            }
        } catch (IllegalArgumentException | RutInvalidoException e) {
            System.out.println("Registro omitido por formato inválido: " + linea + " (" + e.getMessage() + ")");
        }
    }

    private static void validarCantidadCampos(String[] partes, int cantidadEsperada) {
        if (partes.length != cantidadEsperada) {
            throw new IllegalArgumentException("Cantidad de campos inválida");
        }
    }

    private static Direccion crearDireccion(String[] partes) {
        return new Direccion(partes[6], partes[7], partes[8], partes[9], partes[10]);
    }
}
