package model;

import util.Validador;

/** Persona externa que presta un servicio a la agencia. */
public class ColaboradorExterno extends Persona implements Registrable {
    private String empresaOrigen;
    private String servicioPrestado;

    public ColaboradorExterno(String nombre, String apellido, Direccion direccion, String telefono,
                              String email, Rut rut, String empresaOrigen, String servicioPrestado) {
        super(nombre, apellido, direccion, telefono, email, rut);
        this.empresaOrigen = Validador.validarTexto(empresaOrigen, "Particular");
        this.servicioPrestado = Validador.validarTexto(servicioPrestado, "No especificado");
    }

    public String getEmpresaOrigen() { return empresaOrigen; }
    public void setEmpresaOrigen(String empresaOrigen) { this.empresaOrigen = Validador.validarTexto(empresaOrigen, "Particular"); }
    public String getServicioPrestado() { return servicioPrestado; }
    public void setServicioPrestado(String servicioPrestado) { this.servicioPrestado = Validador.validarTexto(servicioPrestado, "No especificado"); }

    @Override
    public String mostrarResumen() {
        return "Colaborador externo | Nombre: " + getNombre() + " " + getApellido()
                + " | RUT: " + getRut()
                + " | Empresa: " + empresaOrigen + " | Servicio: " + servicioPrestado;
    }

    @Override
    public String toString() {
        return mostrarResumen();
    }
}
