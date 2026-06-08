package model;

/**
 * Clase que representa la dirección geográfica de una persona o entidad.
 * Componente utilizado para establecer relaciones de composición (agregación).
 * * @author Gabriel
 */
public class Direccion {

    private String region;
    private String ciudad;
    private String comuna;
    private String calle;
    private int numeroCalle;

    /**
     * Constructor de la clase Direccion.
     * @param region Región geográfica.
     * @param ciudad Ciudad base.
     * @param comuna Comuna residencial.
     * @param calle Nombre de la calle.
     * @param numeroCalle Altura o número de la propiedad.
     */
    public Direccion(String region, String ciudad, String comuna, String calle, int numeroCalle) {
        this.region = region;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.calle = calle;
        this.numeroCalle = numeroCalle;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(int numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    /**
     * Devuelve una cadena de texto con el formato de la dirección.
     * @return Formato de texto de la dirección.
     */
    @Override
    public String toString() {
        return "Direccion{" +
                "Region='" + region + '\'' +
                ", Ciudad='" + ciudad + '\'' +
                ", Comuna='" + comuna + '\'' +
                ", Calle='" + calle + '\'' +
                ", numeroCalle=" + numeroCalle +
                '}';
    }
}