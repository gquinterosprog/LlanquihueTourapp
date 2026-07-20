package model;

import util.Validador;

/** Colaborador interno de la agencia. */
public class Empleado extends Persona implements Registrable {
    private String area;
    private String superior;
    private String jornada;

    public Empleado(String nombre, String apellido, Direccion direccion, String telefono,
                    String email, Rut rut, String area, String superior, String jornada) {
        super(nombre, apellido, direccion, telefono, email, rut);
        setArea(area);
        setSuperior(superior);
        setJornada(jornada);
    }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = Validador.validarTexto(area, "área"); }
    public String getSuperior() { return superior; }
    public void setSuperior(String superior) { this.superior = Validador.validarTexto(superior, "superior"); }
    public String getJornada() { return jornada; }
    public void setJornada(String jornada) { this.jornada = Validador.validarTexto(jornada, "jornada"); }

    @Override
    public String mostrarResumen() {
        return "Empleado interno | Nombre: " + getNombre() + " " + getApellido()
                + " | RUT: " + getRut()
                + " | Área: " + area + " | Jornada: " + jornada;
    }

    @Override
    public String toString() {
        return mostrarResumen();
    }
}
