package model;

import data.Validador;

/**
 * Clase derivada que hereda los atributos de Persona y añade la lógica laboral.
 * Implementa la interfaz Registrable.
 * @author Gabriel
 */
public class Empleado extends Persona implements Registrable {
    private String area;
    private String superior;
    private String jornada;

    public Empleado(String nombre, String apellido, Direccion direccion, String telefono, String email, String rut, String area, String superior, String jornada) {
        super(nombre, apellido, direccion, telefono, email, rut);
        this.area = Validador.validarTexto(area, "General");
        this.jornada = Validador.validarTexto(jornada, "No asignada");
        this.superior = superior != null ? superior.trim() : "Sin asignar";
    }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public String getSuperior() { return superior; }
    public void setSuperior(String superior) { this.superior = superior; }
    public String getJornada() { return jornada; }
    public void setJornada(String jornada) { this.jornada = jornada; }

    @Override
    public String mostrarResumen() {
        return "Empleado de Planta - Nombre: " + getNombre() + " " + getApellido() + " | RUT: " + getRut() + " | Área: " + area + " | Jornada: " + jornada;
    }
}