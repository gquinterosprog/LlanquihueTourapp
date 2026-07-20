package model;

import util.Validador;

/** Dirección asociada a una persona de la agencia. */
public class Direccion {
    private String region;
    private String ciudad;
    private String comuna;
    private String calle;
    private int numeroCalle;

    public Direccion(String region, String ciudad, String comuna, String calle, String numeroCalle) {
        setRegion(region);
        setCiudad(ciudad);
        setComuna(comuna);
        setCalle(calle);
        setNumeroCalle(Validador.validarEntero(numeroCalle, "número de calle"));
    }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = Validador.validarTexto(region, "región"); }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = Validador.validarTexto(ciudad, "ciudad"); }
    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = Validador.validarTexto(comuna, "comuna"); }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = Validador.validarTexto(calle, "calle"); }
    public int getNumeroCalle() { return numeroCalle; }
    public void setNumeroCalle(int numeroCalle) {
        if (numeroCalle <= 0) {
            throw new IllegalArgumentException("El número de calle debe ser mayor que cero.");
        }
        this.numeroCalle = numeroCalle;
    }

    @Override
    public String toString() {
        return calle + " " + numeroCalle + ", " + comuna + ", " + ciudad + ", " + region;
    }
}
