# Proyecto Llanquihue Tour

Este proyecto es una aplicación básica en Java para la gestión de clientes y trabajadores de la empresa "Llanquihue Tour", desarrollada para la asignatura de Programación Orientada a Objetos.

## Estructura del Código

El proyecto está organizado en dos paquetes dentro de la carpeta `src`:

* **`model`**: Contiene las clases que definen los objetos del sistema.
* **`app`**: Contiene la clase `Main` que ejecuta la aplicación.

## Conceptos de POO Aplicados

En el código se implementaron los siguientes pilares solicitados en la evaluación:

1. **Encapsulamiento**: En todas las clases (`Direccion`, `Persona`, `Empleado`) los atributos se definieron como privados (`private`) y su acceso se controla estrictamente mediante métodos getters y setters de estructura estándar.
2. **Composición**: La clase `Persona` tiene como atributo un objeto de tipo `Direccion`. Esto establece una relación de composición, donde no se puede crear una persona sin definir primero su ubicación geográfica.
3. **Herencia**: La clase `Empleado` hereda de la clase `Persona` (`extends`). Esto permite reutilizar los atributos comunes (nombre, rut, teléfono, etc.) y añadir los campos específicos del trabajo (área, jefe directo y jornada), llamando al constructor del padre mediante `super()`.

## Ejecución

Para probar el funcionamiento del sistema, basta con ejecutar la clase `Main.java`.

El programa instanciará un cliente y dos empleados (un guía y un administrativo), pasándoles sus respectivas direcciones por parámetro. Finalmente, mostrará los datos ordenados en la consola utilizando la sobrescritura del método `toString()`.

---
**Alumno:** Gabriel Quinteros

**Profesor:** Pablo Vilches