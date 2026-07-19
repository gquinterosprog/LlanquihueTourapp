package ui;

import javax.swing.JOptionPane;
import model.Cliente;
import model.ColaboradorExterno;
import model.Direccion;
import model.Empleado;
import model.GuiaTuristico;
import model.Proveedor;
import model.Registrable;
import model.Rut;
import model.Vehiculo;
import util.RutInvalidoException;
import util.Validador;

public class FormularioEntidades {
    private String nombre;
    private String apellido;
    private Rut rut;
    private String telefono;
    private String email;
    private Direccion direccion;

    public Registrable crearGuiaTuristico() {
        if (!pedirDatosPersona("guía turístico")) return null;
        String especialidad = pedirTexto("Ingrese la especialidad del guía:");
        if (especialidad == null) return null;
        String idiomas = pedirTexto("Ingrese los idiomas que domina:");
        if (idiomas == null) return null;
        Integer experiencia = pedirEnteroNoNegativo("Ingrese años de experiencia:");
        if (experiencia == null) return null;
        return new GuiaTuristico(nombre, apellido, direccion, telefono, email, rut,
                especialidad, idiomas, experiencia);
    }

    public Registrable crearVehiculo() {
        String patente = pedirPatente();
        if (patente == null) return null;
        String modelo = pedirTexto("Ingrese marca y modelo:");
        if (modelo == null) return null;
        Integer capacidad = pedirEnteroPositivo("Ingrese capacidad de pasajeros:");
        if (capacidad == null) return null;
        return new Vehiculo(patente, modelo, capacidad);
    }

    public Registrable crearColaboradorExterno() {
        if (!pedirDatosPersona("colaborador externo")) return null;
        String empresa = pedirTexto("Ingrese la empresa de origen:");
        if (empresa == null) return null;
        String servicio = pedirTexto("Ingrese el servicio prestado:");
        if (servicio == null) return null;
        return new ColaboradorExterno(nombre, apellido, direccion, telefono, email, rut,
                empresa, servicio);
    }

    public Registrable crearEmpleado() {
        if (!pedirDatosPersona("empleado")) return null;
        String area = pedirTexto("Ingrese el área del empleado:");
        if (area == null) return null;
        String superior = pedirTexto("Ingrese el nombre de su superior:");
        if (superior == null) return null;
        String jornada = pedirTexto("Ingrese la jornada laboral:");
        if (jornada == null) return null;
        return new Empleado(nombre, apellido, direccion, telefono, email, rut,
                area, superior, jornada);
    }

    public Registrable crearCliente() {
        if (!pedirDatosPersona("cliente")) return null;
        String preferencia = pedirTexto("Ingrese su preferencia turística:");
        if (preferencia == null) return null;
        String canal = pedirTexto("Ingrese el canal de contacto preferido:");
        if (canal == null) return null;
        return new Cliente(nombre, apellido, direccion, telefono, email, rut,
                preferencia, canal);
    }

    public Registrable crearProveedor() {
        if (!pedirDatosPersona("proveedor")) return null;
        String empresa = pedirTexto("Ingrese el nombre de la empresa:");
        if (empresa == null) return null;
        String servicio = pedirTexto("Ingrese el servicio ofrecido:");
        if (servicio == null) return null;
        return new Proveedor(nombre, apellido, direccion, telefono, email, rut,
                empresa, servicio);
    }

    private boolean pedirDatosPersona(String tipo) {
        nombre = pedirNombre("Ingrese el nombre del " + tipo + ":");
        if (nombre == null) return false;
        apellido = pedirNombre("Ingrese el apellido del " + tipo + ":");
        if (apellido == null) return false;
        rut = pedirRut();
        if (rut == null) return false;
        telefono = pedirTelefono();
        if (telefono == null) return false;
        email = pedirCorreo();
        if (email == null) return false;
        direccion = pedirDireccion();
        return direccion != null;
    }

    private Direccion pedirDireccion() {
        String region = pedirTexto("Ingrese la región:");
        if (region == null) return null;
        String ciudad = pedirTexto("Ingrese la ciudad:");
        if (ciudad == null) return null;
        String comuna = pedirTexto("Ingrese la comuna:");
        if (comuna == null) return null;
        String calle = pedirTexto("Ingrese la calle:");
        if (calle == null) return null;
        Integer numero = pedirEnteroPositivo("Ingrese el número de la dirección:");
        if (numero == null) return null;
        return new Direccion(region, ciudad, comuna, calle, String.valueOf(numero));
    }

    private String pedirPatente() {
        while (true) {
            String texto = pedirTexto("Ingrese la patente (ejemplo: XY8294):");
            if (texto == null) return null;
            if (Validador.esPatenteValida(texto)) return texto.toUpperCase();
            mostrarError("La patente debe tener 6 letras o números, sin símbolos.");
        }
    }

    private String pedirTelefono() {
        while (true) {
            String texto = pedirTexto("Ingrese el teléfono (8 o 9 dígitos):");
            if (texto == null) return null;
            if (Validador.esTelefonoValido(texto)) return texto;
            mostrarError("El teléfono debe contener solamente 8 o 9 dígitos.");
        }
    }

    private String pedirCorreo() {
        while (true) {
            String texto = pedirTexto("Ingrese el correo electrónico:");
            if (texto == null) return null;
            if (Validador.esCorreoValido(texto)) return texto;
            mostrarError("Ingrese un correo con @ y punto, sin espacios.");
        }
    }

    private Rut pedirRut() {
        while (true) {
            String texto = pedirTexto("Ingrese el RUT (ejemplo: 12345678-5):");
            if (texto == null) return null;
            try {
                return new Rut(texto);
            } catch (RutInvalidoException e) {
                mostrarError(e.getMessage());
            }
        }
    }

    private String pedirNombre(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje);
            if (texto == null) return null;
            if (Validador.esSoloLetras(texto)) return texto.trim();
            mostrarError("Ingrese solo letras y espacios.");
        }
    }

    private String pedirTexto(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje);
            if (texto == null) return null;
            if (texto.contains(";")) {
                mostrarError("No use punto y coma (;).");
            } else if (!texto.trim().isEmpty()) {
                return texto.trim();
            } else {
                mostrarError("El campo es obligatorio.");
            }
        }
    }

    private Integer pedirEnteroNoNegativo(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje);
            if (texto == null) return null;
            try {
                int numero = Integer.parseInt(texto.trim());
                if (numero >= 0) return numero;
            } catch (NumberFormatException e) {
                mostrarError("Ingrese un número entero.");
            }
        }
    }

    private Integer pedirEnteroPositivo(String mensaje) {
        while (true) {
            Integer numero = pedirEnteroNoNegativo(mensaje);
            if (numero == null || numero > 0) return numero;
            mostrarError("El número debe ser mayor que cero.");
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Validación", JOptionPane.ERROR_MESSAGE);
    }
}
