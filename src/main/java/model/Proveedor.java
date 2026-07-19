package model;

import util.Validador;

public class Proveedor extends Persona implements Registrable {
    private String empresa;
    private String servicioOfrecido;

    public Proveedor(String nombre, String apellido, Direccion direccion, String telefono,
                     String email, Rut rut, String empresa, String servicioOfrecido) {
        super(nombre, apellido, direccion, telefono, email, rut);
        this.empresa = Validador.validarTexto(empresa, "Proveedor independiente");
        this.servicioOfrecido = Validador.validarTexto(servicioOfrecido, "No especificado");
    }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = Validador.validarTexto(empresa, "Proveedor independiente"); }
    public String getServicioOfrecido() { return servicioOfrecido; }
    public void setServicioOfrecido(String servicioOfrecido) {
        this.servicioOfrecido = Validador.validarTexto(servicioOfrecido, "No especificado");
    }

    @Override
    public String mostrarResumen() {
        return "Proveedor | Contacto: " + getNombre() + " " + getApellido() + " | RUT: " + getRut()
                + " | Empresa: " + empresa + " | Servicio: " + servicioOfrecido;
    }

    @Override
    public String toString() {
        return mostrarResumen();
    }
}
