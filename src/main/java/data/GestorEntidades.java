package data;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.ColaboradorExterno;
import model.Direccion;
import model.Empleado;
import model.GuiaTuristico;
import model.Persona;
import model.Proveedor;
import model.Registrable;
import model.Vehiculo;

public class GestorEntidades {
    private final List<Registrable> recursos = new ArrayList<>();
    private final String rutaArchivo;

    public GestorEntidades(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        List<Registrable> datosGuardados = LectorArchivo.cargarDatos(rutaArchivo);
        for (Registrable recurso : datosGuardados) {
            recursos.add(recurso);
        }
    }

    public String registrarRecurso(Registrable recurso) {
        if (recurso == null) {
            return "Registro cancelado.";
        }
        if (esDuplicado(recurso)) {
            return "Ya existe una entidad con el mismo RUT o patente.";
        }

        String linea = convertirARegistro(recurso);
        if (!LectorArchivo.guardarRegistro(rutaArchivo, linea)) {
            return "No fue posible guardar el registro.";
        }

        recursos.add(recurso);
        return "Entidad registrada y guardada correctamente.";
    }

    public String generarReporteRecursos() {
        return generarReporte(recursos, "ENTIDADES REGISTRADAS - LLANQUIHUE TOUR");
    }

    public String buscarRecursos(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return "Debe ingresar un criterio de búsqueda.";
        }

        List<Registrable> encontrados = new ArrayList<>();
        for (Registrable recurso : recursos) {
            if (recurso.mostrarResumen().contains(criterio)) {
                encontrados.add(recurso);
            }
        }
        return generarReporte(encontrados, "RESULTADOS PARA: " + criterio);
    }

    private String generarReporte(List<Registrable> listado, String titulo) {
        if (listado.isEmpty()) {
            return "No se encontraron entidades para mostrar.";
        }

        String reporte = titulo + "\n\n";
        for (Registrable recurso : listado) {
            reporte += recurso.mostrarResumen() + "\n";
            reporte += "----------------------------------------\n";
        }
        return reporte;
    }

    public String generarEstadisticasPorTipo() {
        int guias = 0;
        int vehiculos = 0;
        int colaboradores = 0;
        int empleados = 0;
        int clientes = 0;
        int proveedores = 0;

        for (Registrable recurso : recursos) {
            if (recurso instanceof GuiaTuristico) {
                guias++;
            } else if (recurso instanceof Vehiculo) {
                vehiculos++;
            } else if (recurso instanceof ColaboradorExterno) {
                colaboradores++;
            } else if (recurso instanceof Empleado) {
                empleados++;
            } else if (recurso instanceof Cliente) {
                clientes++;
            } else if (recurso instanceof Proveedor) {
                proveedores++;
            }
        }

        return "ESTADÍSTICAS DE ENTIDADES\n\n"
                + "Guías turísticos: " + guias + "\n"
                + "Vehículos: " + vehiculos + "\n"
                + "Colaboradores externos: " + colaboradores + "\n"
                + "Empleados: " + empleados + "\n"
                + "Clientes: " + clientes + "\n"
                + "Proveedores: " + proveedores + "\n"
                + "Total: " + recursos.size();
    }

    private boolean esDuplicado(Registrable nuevo) {
        for (Registrable existente : recursos) {
            if (nuevo instanceof Persona nuevaPersona && existente instanceof Persona personaExistente) {
                if (nuevaPersona.getRut().toString().equalsIgnoreCase(personaExistente.getRut().toString())) {
                    return true;
                }
            }
            if (nuevo instanceof Vehiculo nuevoVehiculo && existente instanceof Vehiculo vehiculoExistente) {
                if (nuevoVehiculo.getPatente().equalsIgnoreCase(vehiculoExistente.getPatente())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String convertirARegistro(Registrable recurso) {
        if (recurso instanceof GuiaTuristico guia) {
            return "GUIA;" + datosPersona(guia) + ";" + guia.getEspecialidad() + ";"
                    + guia.getIdiomas() + ";" + guia.getAniosExperiencia();
        }
        if (recurso instanceof Vehiculo vehiculo) {
            return "VEHICULO;" + vehiculo.getPatente() + ";" + vehiculo.getMarcaModelo() + ";"
                    + vehiculo.getCapacidadPasajeros();
        }
        if (recurso instanceof ColaboradorExterno colaborador) {
            return "COLABORADOR;" + datosPersona(colaborador) + ";" + colaborador.getEmpresaOrigen()
                    + ";" + colaborador.getServicioPrestado();
        }
        if (recurso instanceof Empleado empleado) {
            return "EMPLEADO;" + datosPersona(empleado) + ";" + empleado.getArea() + ";"
                    + empleado.getSuperior() + ";" + empleado.getJornada();
        }
        if (recurso instanceof Cliente cliente) {
            return "CLIENTE;" + datosPersona(cliente) + ";" + cliente.getPreferenciaTuristica()
                    + ";" + cliente.getCanalContacto();
        }
        if (recurso instanceof Proveedor proveedor) {
            return "PROVEEDOR;" + datosPersona(proveedor) + ";" + proveedor.getEmpresa() + ";"
                    + proveedor.getServicioOfrecido();
        }
        throw new IllegalArgumentException("Tipo de entidad no compatible.");
    }

    private String datosPersona(Persona persona) {
        Direccion direccion = persona.getDireccion();
        return persona.getNombre() + ";" + persona.getApellido() + ";" + persona.getRut().toString() + ";"
                + persona.getTelefono() + ";" + persona.getEmail() + ";" + direccion.getRegion() + ";"
                + direccion.getCiudad() + ";" + direccion.getComuna() + ";" + direccion.getCalle() + ";"
                + direccion.getNumeroCalle();
    }

    public int obtenerCantidadRecursos() {
        return recursos.size();
    }

    public List<Registrable> getRecursos() {
        return new ArrayList<>(recursos);
    }
}
