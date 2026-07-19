package model;

import util.Validador;

/** Vehículo de transporte registrable */
public class Vehiculo implements Registrable {
    private String patente;
    private String marcaModelo;
    private int capacidadPasajeros;

    public Vehiculo(String patente, String marcaModelo, int capacidadPasajeros) {
        setPatente(patente);
        this.marcaModelo = Validador.validarTexto(marcaModelo, "Desconocido");
        this.capacidadPasajeros = Math.max(capacidadPasajeros, 1);
    }

    public String getPatente() { return patente; }
    public void setPatente(String patente) {
        if (!Validador.esPatenteValida(patente)) {
            throw new IllegalArgumentException("La patente debe tener 6 letras o números.");
        }
        this.patente = patente.toUpperCase();
    }
    public String getMarcaModelo() { return marcaModelo; }
    public void setMarcaModelo(String marcaModelo) { this.marcaModelo = Validador.validarTexto(marcaModelo, "Desconocido"); }
    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public void setCapacidadPasajeros(int capacidadPasajeros) { this.capacidadPasajeros = Math.max(capacidadPasajeros, 1); }

    @Override
    public String mostrarResumen() {
        return "Vehículo | Patente: " + patente + " | Modelo: " + marcaModelo
                + " | Capacidad: " + capacidadPasajeros + " pasajeros";
    }

    @Override
    public String toString() {
        return mostrarResumen();
    }
}
