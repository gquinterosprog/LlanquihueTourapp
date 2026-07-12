package model;

import data.Validador;

/** Dirección asociada a una persona de la agencia. */
public class Direccion {
    private String region;
    private String ciudad;
    private String comuna;
    private String calle;
    private int numeroCalle;

    public Direccion(String region, String ciudad, String comuna, String calle, String numeroCalle) {
        this.region = Validador.validarTexto(region, "Sin región");
        this.ciudad = Validador.validarTexto(ciudad, "Sin ciudad");
        this.comuna = Validador.validarTexto(comuna, "Sin comuna");
        this.calle = Validador.validarTexto(calle, "Sin calle");
        this.numeroCalle = Validador.validarEntero(numeroCalle, 0);
    }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = Validador.validarTexto(region, "Sin región"); }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = Validador.validarTexto(ciudad, "Sin ciudad"); }
    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = Validador.validarTexto(comuna, "Sin comuna"); }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = Validador.validarTexto(calle, "Sin calle"); }
    public int getNumeroCalle() { return numeroCalle; }
    public void setNumeroCalle(int numeroCalle) { this.numeroCalle = Math.max(numeroCalle, 0); }

    @Override
    public String toString() {
        return calle + " " + numeroCalle + ", " + comuna + ", " + ciudad + ", " + region;
    }
}
