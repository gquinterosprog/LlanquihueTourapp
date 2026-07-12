package model;

/**
 * Clase que representa a los guías encargados de las excursiones en terreno.
 * Hereda de Persona e implementa la interfaz Registrable.
 * @author Gabriel
 */
public class GuiaTuristico extends Persona implements Registrable {
    private String especialidad;
    private String idiomas;
    private int aniosExperiencia;

    public GuiaTuristico(String nombre, String apellido, Direccion direccion, String telefono, String email, String rut, String especialidad, String idiomas, int aniosExperiencia) {
        super(nombre, apellido, direccion, telefono, email, rut);
        this.especialidad = especialidad != null ? especialidad.trim() : "General";
        this.idiomas = idiomas != null ? idiomas.trim() : "Español";
        this.aniosExperiencia = aniosExperiencia >= 0 ? aniosExperiencia : 0;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getIdiomas() { return idiomas; }
    public void setIdiomas(String idiomas) { this.idiomas = idiomas; }
    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }

    @Override
    public String mostrarResumen() {
        return "Guía Turístico - Nombre: " + getNombre() + " " + getApellido() + " | Especialidad: " + especialidad + " | Idiomas: " + idiomas + " | Experiencia: " + aniosExperiencia + " años";
    }
}