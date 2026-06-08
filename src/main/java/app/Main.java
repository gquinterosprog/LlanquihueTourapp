package app;

import model.Direccion;
import model.Persona;
import model.Empleado;

public class Main {
    public static void main(String[] args) {


        Direccion dirCliente = new Direccion("Los Lagos", "Llanquihue", "Llanquihue", "San Martin", 123);
        Direccion dirGuia = new Direccion("Los Lagos", "Puerto Varas", "Puerto Varas", "Del Salvador", 456);
        Direccion dirAdmin = new Direccion("Los Lagos", "Frutillar", "Frutillar", "Philippi", 789);


        Persona cliente = new Persona("Juan", "Perez", dirCliente, "911112222", "juan@correo.cl", "12345678-9");
        Empleado guia = new Empleado("Maria", "Soto", dirGuia, "933334444", "maria@llanquihuetour.cl", "18765432-1", "Turismo", "Carlos Gomez", "Completa");
        Empleado admin = new Empleado("Carlos", "Gomez", dirAdmin, "955556666", "carlos@llanquihuetour.cl", "15444333-2", "Administracion", "Gerencia", "Media");


        System.out.println("  DATOS DEL CLIENTE  ");
        System.out.println(cliente.toString());

        System.out.println("\n  DATOS DEL GUIA  ");
        System.out.println(guia.toString());

        System.out.println("\n  DATOS DEL ADMINISTRATIVO  ");
        System.out.println(admin.toString());

    }
}