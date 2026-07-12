package model;

import data.Validador;

/** Clase base para las personas relacionadas con la agencia. */
public abstract class Persona {
    private String nombre;
    private String apellido;
    private Direccion direccion;
    private String telefono;
    private String email;
    private String rut;

    protected Persona(String nombre, String apellido, Direccion direccion,
                      String telefono, String email, String rut) {
        this.nombre = Validador.validarTexto(nombre, "Sin nombre");
        this.apellido = Validador.validarTexto(apellido, "Sin apellido");
        this.direccion = direccion == null
                ? new Direccion("Sin región", "Sin ciudad", "Sin comuna", "Sin calle", "0")
                : direccion;
        this.telefono = Validador.validarTexto(telefono, "Sin teléfono");
        this.email = Validador.validarTexto(email, "Sin correo");
        this.rut = Validador.validarTexto(rut, "Sin RUT");
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = Validador.validarTexto(nombre, "Sin nombre"); }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = Validador.validarTexto(apellido, "Sin apellido"); }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { if (direccion != null) this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = Validador.validarTexto(telefono, "Sin teléfono"); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = Validador.validarTexto(email, "Sin correo"); }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = Validador.validarTexto(rut, "Sin RUT"); }

    @Override
    public String toString() {
        return nombre + " " + apellido + " | RUT: " + rut;
    }
}
