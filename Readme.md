# Proyecto Llanquihue Tour

Este proyecto es una aplicación modular en Java para la gestión de colaboradores de la empresa "Llanquihue Tour", desarrollada para la asignatura de Programación Orientada a Objetos (Desarrollo Orientado a Objetos I). El sistema automatiza procesos manuales permitiendo cargar registros desde archivos externos y realizar búsquedas o filtros eficientes.

## Estructura del Código

El proyecto está organizado en cuatro paquetes dentro de la carpeta `src` para garantizar una arquitectura limpia y modular:

* **`model`**: Contiene las clases que definen las entidades del sistema (`Persona`, `Empleado`, `Direccion`).
* **`service`**: Capa de lógica de negocio que gestiona las operaciones sobre los datos con la clase `AgenciaService`.
* **`util`**: Contiene componentes utilitarios de persistencia, específicamente la clase `LectorArchivo`.
* **`app`**: Contiene la clase `Main` que actúa como el orquestador y ejecuta la aplicación.

## Conceptos de POO Aplicados

En el código se implementaron los siguientes pilares y buenas prácticas solicitados en la evaluación:

1. **Encapsulamiento**: En todas las clases del modelo (`Direccion`, `Persona`, `Empleado`) los atributos se definieron como privados (`private`) y su acceso se controla estrictamente mediante métodos getters y setters de estructura estándar.
2. **Composición**: La clase `Persona` tiene como atributo un objeto de tipo `Direccion`. Esto establece una relación de composición, donde un objeto del modelo no se instancia sin definir primero su ubicación geográfica.
3. **Herencia**: La clase `Empleado` hereda de la clase `Persona` (`extends`). Esto permite reutilizar los atributos comunes (nombre, rut, teléfono, etc.) y añadir los campos específicos del trabajo, llamando al constructor del padre mediante `super()`.
4. **Validación y Robustez (Try-Catch)**:
    * Se aplicó control de excepciones (`IOException`) con *try-with-resources* en la lectura física para asegurar el cierre automático del archivo y evitar fugas de memoria.
    * Se incorporó validación defensiva en el constructor (`IllegalArgumentException`) atrapada mediante bloques `try-catch` internos para advertir datos no válidos en consola y asignar valores por defecto sin interrumpir la ejecución del sistema.
5. **Colecciones Dinámicas**: Se utiliza la estructura `ArrayList<Empleado>` para manipular y almacenar dinámicamente en memoria la información procesada.

## Ejecución

Para probar el funcionamiento del sistema, basta con ejecutar la clase `Main.java`.

Al iniciar, el programa buscará automáticamente el archivo externo `empleados.txt` en la raíz del proyecto. La clase `LectorArchivo` procesará de forma automatizada las líneas de texto plano separadas por comas, construyendo los objetos correspondientes para rellenar la colección. Luego, a través de `AgenciaService`, se desplegará un menú en la consola que le permite al usuario listar la totalidad de los colaboradores registrados (`toString()`) o aplicar filtros dinámicos de búsqueda por área específica.

---
**Alumno:** Gabriel Quinteros

**Profesor:** Pablo Vilches