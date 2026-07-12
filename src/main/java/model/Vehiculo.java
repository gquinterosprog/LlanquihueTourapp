package model;

import data.Validador;

/** Vehículo de transporte. No es Persona, pero es registrable. */
public class Vehiculo implements Registrable {
    private String patente;
    private String marcaModelo;
    private int capacidadPasajeros;

    public Vehiculo(String patente, String marcaModelo, int capacidadPasajeros) {
        this.patente = Validador.validarTexto(patente, "Sin patente").toUpperCase();
        this.marcaModelo = Validador.validarTexto(marcaModelo, "Desconocido");
        this.capacidadPasajeros = Math.max(capacidadPasajeros, 1);
    }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = Validador.validarTexto(patente, "Sin patente").toUpperCase(); }
    public String getMarcaModelo() { return marcaModelo; }
    public void setMarcaModelo(String marcaModelo) { this.marcaModelo = Validador.validarTexto(marcaModelo, "Desconocido"); }
    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public void setCapacidadPasajeros(int capacidadPasajeros) { this.capacidadPasajeros = Math.max(capacidadPasajeros, 1); }

    @Override
    public String mostrarResumen() {
        return "Vehículo | Patente: " + patente + " | Modelo: " + marcaModelo
                + " | Capacidad: " + capacidadPasajeros + " pasajeros";
    }
}
