package model;

import util.Validador;

/** Guía encargado de actividades turísticas. */
public class GuiaTuristico extends Persona implements Registrable {
    private String especialidad;
    private String idiomas;
    private int aniosExperiencia;

    public GuiaTuristico(String nombre, String apellido, Direccion direccion, String telefono,
                         String email, Rut rut, String especialidad, String idiomas,
                         int aniosExperiencia) {
        super(nombre, apellido, direccion, telefono, email, rut);
        setEspecialidad(especialidad);
        setIdiomas(idiomas);
        setAniosExperiencia(aniosExperiencia);
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = Validador.validarTexto(especialidad, "especialidad"); }
    public String getIdiomas() { return idiomas; }
    public void setIdiomas(String idiomas) { this.idiomas = Validador.validarTexto(idiomas, "idiomas"); }
    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) {
        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String mostrarResumen() {
        return "Guía turístico | Nombre: " + getNombre() + " " + getApellido()
                + " | RUT: " + getRut()
                + " | Especialidad: " + especialidad + " | Idiomas: " + idiomas
                + " | Experiencia: " + aniosExperiencia + " años";
    }

    @Override
    public String toString() {
        return mostrarResumen();
    }
}
