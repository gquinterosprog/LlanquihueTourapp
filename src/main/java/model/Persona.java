package model;

/**
 * Clase base que define el comportamiento y datos generales de una Persona.
 * Mantiene una relación de composición con la clase Direccion.
 * * @author Gabriel
 */
public class Persona {
    private String nombre;
    private String apellido;
    private Direccion direccion;
    private String telefono;
    private String email;
    private String rut;

    /**
     * Constructor para inicializar una nueva Persona.
     * @param nombre Nombre de pila.
     * @param apellido Apellidos paterno/materno.
     * @param direccion Objeto Direccion (Relación de Composición).
     * @param telefono Teléfono de red fija o móvil.
     * @param email Dirección de correo electrónico.
     * @param rut Rol Único Tributario (Identificador único).
     */
    public Persona(String nombre, String apellido, Direccion direccion, String telefono, String email, String rut) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.rut = rut;
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