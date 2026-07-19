package model;

import util.RutInvalidoException;

public class Rut {
    private String numero;

    public Rut(String numero) throws RutInvalidoException {
        if (numero == null || !numero.matches("[0-9]{8}-[0-9kK]")) {
            throw new RutInvalidoException("El RUT debe tener el formato XXXXXXXX-Y.");
        }
        this.numero = numero.toUpperCase();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) throws RutInvalidoException {
        if (numero == null || !numero.matches("[0-9]{8}-[0-9kK]")) {
            throw new RutInvalidoException("El RUT debe tener el formato XXXXXXXX-Y.");
        }
        this.numero = numero.toUpperCase();
    }

    @Override
    public String toString() {
        return numero;
    }
}
