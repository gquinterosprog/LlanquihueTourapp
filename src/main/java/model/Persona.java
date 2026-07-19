package model;

import util.Validador;

/** Clase base para las personas relacionadas con la agencia. */
public abstract class Persona {
    private String nombre;
    private String apellido;
    private Direccion direccion;
    private String telefono;
    private String email;
    private Rut rut;

    protected Persona(String nombre, String apellido, Direccion direccion,
                      String telefono, String email, Rut rut) {
        this.nombre = Validador.validarTexto(nombre, "Sin nombre");
        this.apellido = Validador.validarTexto(apellido, "Sin apellido");
        if (direccion == null) {
            this.direccion = new Direccion("Sin región", "Sin ciudad", "Sin comuna", "Sin calle", "0");
        } else {
            this.direccion = direccion;
        }
        setTelefono(telefono);
        setEmail(email);
        this.rut = rut;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = Validador.validarTexto(nombre, "Sin nombre"); }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = Validador.validarTexto(apellido, "Sin apellido"); }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { if (direccion != null) this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {
        if (!Validador.esTelefonoValido(telefono)) {
            throw new IllegalArgumentException("El teléfono debe tener 8 o 9 dígitos.");
        }
        this.telefono = telefono.trim();
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (!Validador.esCorreoValido(email)) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }
        this.email = email.trim();
    }
    public Rut getRut() { return rut; }
    public void setRut(Rut rut) { if (rut != null) this.rut = rut; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " | RUT: " + rut
                + " | Teléfono: " + telefono + " | Correo: " + email
                + " | Dirección: " + direccion;
    }
}
