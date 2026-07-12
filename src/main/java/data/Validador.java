package data;

/** Métodos reutilizables para validar los datos ingresados. */
public final class Validador {
    private Validador() {
    }

    public static String validarTexto(String texto, String valorDefecto) {
        return texto == null || texto.isBlank() ? valorDefecto : texto.trim();
    }

    public static int validarEntero(String texto, int valorDefecto) {
        try {
            int valor = Integer.parseInt(texto.trim());
            return valor >= 0 ? valor : valorDefecto;
        } catch (NumberFormatException | NullPointerException e) {
            return valorDefecto;
        }
    }

    public static boolean esSoloLetras(String texto) {
        return texto != null && texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }
}
