# Proyecto Llanquihue Tour - Semana 5

Este proyecto consiste en una aplicación de consola en Java diseñada para gestionar el personal de la agencia "Llanquihue Tour". El sistema automatiza el trabajo manual permitiendo cargar todos los datos de los trabajadores desde un archivo de texto externo, almacenarlos en memoria y realizar búsquedas de forma rápida.

## Estructura del Código

Para que el proyecto sea ordenado y modular, el código está organizado en cuatro paquetes dentro de la carpeta `src`:

* **`model`**: Contiene las clases base del sistema (`Persona`, `Empleado` y `Direccion`).
* **`service`**: Incluye la clase `AgenciaService`, que maneja la lógica de las búsquedas y los listados de empleados en memoria.
* **`util`**: Contiene la clase `LectorArchivo`, encargada exclusivamente de abrir y procesar el archivo de texto.
* **`app`**: Contiene la clase `Main` que inicia la aplicación y despliega el menú.

## Conceptos de POO Aplicados

En el desarrollo se aplicaron los siguientes requerimientos de programación orientada a objetos:

1. **Encapsulamiento**: Todos los atributos de las clases son privados (`private`) y se accede a ellos estrictamente a través de métodos getters y setters estándar.
2. **Composición**: La clase `Persona` tiene un atributo de tipo `Direccion`. Esto significa que no se puede crear un trabajador sin asignarle primero su ubicación.
3. **Herencia**: La clase `Empleado` hereda de `Persona` (`extends`). Aprovecha los campos comunes (como nombre y rut) mediante el uso de `super()` en su constructor y añade los datos propios de su puesto de trabajo.
4. **Manejo de Excepciones (Try-Catch)**:
   * Se usó un bloque `try-catch` para controlar la lectura del archivo `.txt`, asegurando que el flujo se cierre bien y no falle el sistema si el archivo no existe.
   * Se añadieron validaciones dentro del constructor usando `try-catch` e `IllegalArgumentException` para detectar si un nombre viene vacío, mostrando una advertencia en la consola y asignando un valor por defecto para que el programa no se caiga.
5. **Colecciones (`ArrayList`)**: Los empleados cargados desde el archivo se guardan en un `ArrayList<Empleado>` para poder manejarlos dinámicamente.

## Ejecución y Menú del Sistema

Para probar el programa, se debe ejecutar la clase `Main.java`. El sistema leerá automáticamente el archivo `empleados.txt` ubicado en la raíz y mostrará un menú interactivo en la consola con las siguientes opciones:

* **Opción 1 (Mostrar todos los colaboradores)**: Recorre la lista completa y muestra en pantalla los datos de todos los empleados ordenados, utilizando el método `toString()`.
* **Opción 2 (Filtrar colaboradores por área)**: El usuario ingresa un área (por ejemplo: Operaciones, Ventas, Administrativo) y el sistema busca las coincidencias (sin importar mayúsculas o minúsculas) para mostrar solo a los trabajadores de ese sector.
* **Opción 3 (Salir)**: Cierra la aplicación de forma segura.

---
**Alumno:** Gabriel Quinteros  
**Profesor:** Pablo Vilches  
**Asignatura:** Desarrollo Orientado a Objetos I