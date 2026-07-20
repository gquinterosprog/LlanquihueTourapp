package model;

import util.Validador;

public class Cliente extends Persona implements Registrable {
    private String preferenciaTuristica;
    private String canalContacto;

    public Cliente(String nombre, String apellido, Direccion direccion, String telefono,
                   String email, Rut rut, String preferenciaTuristica, String canalContacto) {
        super(nombre, apellido, direccion, telefono, email, rut);
        setPreferenciaTuristica(preferenciaTuristica);
        setCanalContacto(canalContacto);
    }

    public String getPreferenciaTuristica() { return preferenciaTuristica; }
    public void setPreferenciaTuristica(String preferenciaTuristica) {
        this.preferenciaTuristica = Validador.validarTexto(preferenciaTuristica, "preferencia turística");
    }
    public String getCanalContacto() { return canalContacto; }
    public void setCanalContacto(String canalContacto) {
        this.canalContacto = Validador.validarTexto(canalContacto, "canal de contacto");
    }

    @Override
    public String mostrarResumen() {
        return "Cliente | Nombre: " + getNombre() + " " + getApellido() + " | RUT: " + getRut()
                + " | Preferencia: " + preferenciaTuristica + " | Contacto: " + canalContacto;
    }

    @Override
    public String toString() {
        return mostrarResumen();
    }
}
