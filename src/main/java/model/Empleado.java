package model;

/**
 * Clase derivada que hereda los atributos de Persona y añade la lógica laboral.
 * Representa a un trabajador activo dentro de Llanquihue Tour.
 * @author Gabriel
 */
public class Empleado extends Persona {
    private String area;
    private String superior;
    private String jornada;

    /**
     * Constructor para crear un Empleado invocando la estructura de la clase base y aplicando validaciones.
     * @param nombre Nombre heredado de Persona.
     * @param apellido Apellido heredado de Persona.
     * @param direccion Dirección heredada de Persona.
     * @param telefono Teléfono heredado de Persona.
     * @param email Email heredado de Persona.
     * @param rut RUT heredado de Persona.
     * @param area Departamento al que pertenece (ej: Administración, Turismo).
     * @param superior Jefe o supervisor directo asignado.
     * @param jornada Régimen horario (ej: Completa, Media).
     */
    public Empleado(String nombre, String apellido, Direccion direccion, String telefono, String email, String rut, String area, String superior, String jornada) {
        super(nombre, apellido, direccion, telefono, email, rut);

        try {
            if (area == null || area.trim().isEmpty()) {
                throw new IllegalArgumentException("El área laboral asignada no puede ser nula o vacía.");
            }
            if (jornada == null || jornada.trim().isEmpty()) {
                throw new IllegalArgumentException("La jornada laboral asignada no puede ser nula o vacía.");
            }
            this.area = area.trim();
            this.jornada = jornada.trim();
        } catch (IllegalArgumentException e) {
            System.out.println(" Error de validación en parámetros de Empleado: " + e.getMessage());
            this.area = "General";
            this.jornada = "No asignada";
        }

        this.superior = superior != null ? superior.trim() : "Sin asignar";
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    /**
     * Sobrescribe el formato de impresión agregando los campos heredados y específicos.
     * Utiliza los getters de la clase padre para acceder a los datos encapsulados.
     * @return Cadena de texto con toda la información consolidada del empleado.
     */
    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", rut='" + getRut() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", direccion=" + getDireccion() +
                ", area='" + area + '\'' +
                ", superior='" + superior + '\'' +
                ", jornada='" + jornada + '\'' +
                '}';
    }
}