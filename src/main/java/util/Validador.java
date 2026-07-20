package util;

public class Validador {

    public static String validarTexto(String texto, String nombreCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " es obligatorio.");
        }
        return texto.trim();
    }

    public static int validarEntero(String texto, String nombreCampo) {
        try {
            int valor = Integer.parseInt(texto.trim());
            if (valor >= 0) {
                return valor;
            }
            throw new IllegalArgumentException("El campo " + nombreCampo + " no puede ser negativo.");
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " debe ser un número entero.");
        }
    }

    public static boolean esSoloLetras(String texto) {
        return texto != null && texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public static boolean esRutValido(String rut) {
        return rut != null && rut.matches("[0-9]{8}-[0-9kK]");
    }

    public static boolean esPatenteValida(String patente) {
        return patente != null && patente.matches("[a-zA-Z0-9]{6}");
    }

    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("[0-9]{8,9}");
    }

    public static boolean esCorreoValido(String correo) {
        return correo != null
                && correo.contains("@")
                && correo.contains(".")
                && !correo.contains(" ")
                && !correo.contains(";");
    }

}
