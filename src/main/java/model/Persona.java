package model;

/**
 * Clase base que define el comportamiento y datos generales de una Persona.
 * Mantiene una relación de composición con la clase Direccion.
 * @author Gabriel
 */
public class Persona {
    private String nombre;
    private String apellido;
    private Direccion direccion;
    private String telefono;
    private String email;
    private String rut;

    /**
     * Constructor para inicializar una nueva Persona con validaciones integradas.
     * @param nombre Nombre de pila.
     * @param apellido Apellidos paterno/materno.
     * @param direccion Objeto Direccion (Relación de Composición).
     * @param telefono Teléfono de red fija o móvil.
     * @param email Dirección de correo electrónico.
     * @param rut Rol Único Tributario (Identificador único).
     */
    public Persona(String nombre, String apellido, Direccion direccion, String telefono, String email, String rut) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre proporcionado no puede ser nulo o vacío.");
            }
            if (apellido == null || apellido.trim().isEmpty()) {
                throw new IllegalArgumentException("El apellido proporcionado no puede ser nulo o vacío.");
            }
            this.nombre = nombre.trim();
            this.apellido = apellido.trim();
        } catch (IllegalArgumentException e) {
            System.out.println(" Error de validación en parámetros de Persona: " + e.getMessage());
            this.nombre = "Sin Nombre";
            this.apellido = "Sin Apellido";
        }

        this.direccion = direccion;
        this.telefono = telefono != null ? telefono.trim() : "";
        this.email = email != null ? email.trim() : "";
        this.rut = rut != null ? rut.trim() : "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Devuelve la información completa del perfil de la persona.
     * @return Cadena de texto con los datos personales encapsulados.
     */
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion=" + direccion +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", rut='" + rut + '\'' +
                '}';
    }
}