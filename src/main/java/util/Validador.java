package util;

public class Validador {

    public static String validarTexto(String texto, String valorDefecto) {
        if (texto == null || texto.trim().isEmpty()) {
            return valorDefecto;
        }
        return texto.trim();
    }

    public static int validarEntero(String texto, int valorDefecto) {
        try {
            int valor = Integer.parseInt(texto.trim());
            if (valor >= 0) {
                return valor;
            }
            return valorDefecto;
        } catch (NumberFormatException | NullPointerException e) {
            return valorDefecto;
        }
    }

    public static boolean esSoloLetras(String texto) {
        return texto != null && texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
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
