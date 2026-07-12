package model;

import data.Validador;

/** Guía encargado de actividades turísticas. */
public class GuiaTuristico extends Persona implements Registrable {
    private String especialidad;
    private String idiomas;
    private int aniosExperiencia;

    public GuiaTuristico(String nombre, String apellido, Direccion direccion, String telefono,
                         String email, String rut, String especialidad, String idiomas,
                         int aniosExperiencia) {
        super(nombre, apellido, direccion, telefono, email, rut);
        this.especialidad = Validador.validarTexto(especialidad, "General");
        this.idiomas = Validador.validarTexto(idiomas, "Español");
        this.aniosExperiencia = Math.max(aniosExperiencia, 0);
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = Validador.validarTexto(especialidad, "General"); }
    public String getIdiomas() { return idiomas; }
    public void setIdiomas(String idiomas) { this.idiomas = Validador.validarTexto(idiomas, "Español"); }
    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = Math.max(aniosExperiencia, 0); }

    @Override
    public String mostrarResumen() {
        return "Guía turístico | Nombre: " + getNombre() + " " + getApellido()
                + " | Especialidad: " + especialidad + " | Idiomas: " + idiomas
                + " | Experiencia: " + aniosExperiencia + " años";
    }
}
