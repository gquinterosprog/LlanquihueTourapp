package model;

import data.Validador;

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

    public Persona(String nombre, String apellido, Direccion direccion, String telefono, String email, String rut) {
        this.nombre = Validador.validarTexto(nombre, "Sin Nombre");
        this.apellido = Validador.validarTexto(apellido, "Sin Apellido");
        this.direccion = direccion;
        this.telefono = telefono != null ? telefono.trim() : "";
        this.email = email != null ? email.trim() : "";
        this.rut = rut != null ? rut.trim() : "";
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    @Override
    public String toString() {
        return "Persona{nombre='" + nombre + "', apellido='" + apellido + "', rut='" + rut + "'}";
    }
}