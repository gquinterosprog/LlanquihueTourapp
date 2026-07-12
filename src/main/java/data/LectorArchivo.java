package data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import model.ColaboradorExterno;
import model.Direccion;
import model.GuiaTuristico;
import model.Registrable;
import model.Vehiculo;

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
                if (linea.isBlank()) continue;
                procesarLinea(linea, recursos);
            }
        } catch (IOException e) {
            System.out.println("No fue posible leer el archivo: " + e.getMessage());
        }
        return recursos;
    }

    public static void guardarRegistro(String rutaArchivo, String datos) {
        try {
            Files.writeString(Path.of(rutaArchivo), datos + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("No fue posible guardar el registro: " + e.getMessage());
        }
    }

    private static void procesarLinea(String linea, List<Registrable> recursos) {
        String[] partes = linea.split(";", -1);
        try {
            switch (partes[0].trim().toUpperCase()) {
                case "GUIA" -> {
                    validarCantidadCampos(partes, 7);
                    recursos.add(new GuiaTuristico(partes[1], partes[2], direccionGuia(), "Sin teléfono",
                            "Sin correo", partes[3], partes[4], partes[5], Integer.parseInt(partes[6])));
                }
                case "VEHICULO" -> {
                    validarCantidadCampos(partes, 4);
                    recursos.add(new Vehiculo(partes[1], partes[2], Integer.parseInt(partes[3])));
                }
                case "COLABORADOR" -> {
                    validarCantidadCampos(partes, 6);
                    recursos.add(new ColaboradorExterno(partes[1], partes[2], direccionColaborador(), "Sin teléfono",
                            "Sin correo", partes[3], partes[4], partes[5]));
                }
                default -> System.out.println("Tipo de registro desconocido: " + partes[0]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Registro omitido por formato inválido: " + linea);
        }
    }

    private static void validarCantidadCampos(String[] partes, int cantidadEsperada) {
        if (partes.length != cantidadEsperada) {
            throw new IllegalArgumentException("Cantidad de campos inválida");
        }
    }

    private static Direccion direccionGuia() {
        return new Direccion("Los Lagos", "Puerto Varas", "Centro", "Del Salvador", "250");
    }

    private static Direccion direccionColaborador() {
        return new Direccion("Los Lagos", "Llanquihue", "Centro", "Costanera", "400");
    }
}
