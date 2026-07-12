# Llanquihue Tour App

Aplicación Java con interfaz gráfica básica para registrar y visualizar entidades de la agencia Llanquihue Tour.

## Funcionalidades

- Registra guías turísticos, vehículos y colaboradores externos mediante `JOptionPane`.
- Almacena todas las entidades en una colección polimórfica `List<Registrable>`.
- Muestra un resumen de cada entidad mediante el método `mostrarResumen()`.
- Usa `instanceof` para aplicar un mensaje operativo específico según el tipo de entidad.
- Guarda cada registro en `datos.txt` y lo vuelve a cargar al iniciar el programa.

## Clases e interfaz principales

- `Persona`: superclase abstracta de las personas de la agencia.
- `GuiaTuristico`, `Empleado` y `ColaboradorExterno`: subclases de `Persona`.
- `Vehiculo`: entidad independiente de la jerarquía de personas.
- `Registrable`: interfaz que define el método `mostrarResumen()`.
- `GestorEntidades`: administra la colección polimórfica.
- `Main`: contiene la interfaz gráfica y el punto de entrada.

## Ejecutar el programa

1. Abre el proyecto en IntelliJ IDEA.
2. Verifica que el JDK configurado sea Java 23.
3. Ejecuta la clase `ui.Main`.
4. Usa el menú para registrar entidades y luego selecciona **Mostrar entidades registradas**.

Los datos se guardan automáticamente en el archivo `datos.txt`, creado en la raíz del proyecto al registrar la primera entidad.
