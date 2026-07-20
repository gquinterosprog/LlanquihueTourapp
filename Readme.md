# Llanquihue Tour App

Prototipo Java para digitalizar la gestión de entidades de la agencia de turismo Llanquihue Tour. Permite registrar, consultar y persistir guías, vehículos, colaboradores, empleados, clientes y proveedores desde una interfaz gráfica construida con `JOptionPane`.

## Funcionalidades

- Registra seis tipos de entidades mediante formularios con validación.
- Solicita dirección, teléfono y correo al usuario; no utiliza datos personales fijos.
- Exige los campos obligatorios y no reemplaza datos faltantes con valores predeterminados.
- Valida el RUT con ocho números, guion y un número o `K` mediante una excepción personalizada.
- Valida patentes de seis caracteres alfanuméricos, teléfonos de 8 o 9 dígitos y correos electrónicos.
- Evita registros duplicados utilizando el RUT o la patente como identificador.
- Busca entidades por nombre, RUT, patente, empresa, servicio u otros datos.
- Muestra reportes y estadísticas por tipo de entidad.
- Guarda los registros en `datos.txt` y los carga nuevamente al iniciar.
- Usa una colección polimórfica `List<Registrable>` y conserva una validación acotada con `instanceof` para las estadísticas.

## Principios de Programación Orientada a Objetos

- **Encapsulamiento:** atributos privados con métodos de acceso.
- **Herencia:** `GuiaTuristico`, `Empleado`, `ColaboradorExterno`, `Cliente` y `Proveedor` heredan de la clase abstracta `Persona`.
- **Composición:** cada persona contiene una `Direccion` y un objeto de valor `Rut`.
- **Interfaces:** las entidades gestionables implementan `Registrable`.
- **Polimorfismo:** el gestor recorre una `List<Registrable>` e invoca `mostrarResumen()` en cada objeto.
- **Excepciones:** `RutInvalidoException` informa cuando el RUT no cumple el formato solicitado.

## Organización del proyecto

```text
src/main/java
├── data
│   ├── GestorEntidades.java
│   └── LectorArchivo.java
├── model
│   ├── Persona.java
│   ├── Direccion.java
│   ├── Rut.java
│   ├── Registrable.java
│   ├── GuiaTuristico.java
│   ├── Vehiculo.java
│   ├── ColaboradorExterno.java
│   ├── Empleado.java
│   ├── Cliente.java
│   └── Proveedor.java
├── ui
    ├── FormularioEntidades.java
    └── Main.java
└── util
    ├── RutInvalidoException.java
    └── Validador.java
```

### Responsabilidades principales

- `Main`: muestra el menú y los resultados.
- `FormularioEntidades`: captura y valida los datos ingresados en la GUI.
- `GestorEntidades`: administra la colección, registra entidades, coordina el archivo, realiza búsquedas y genera reportes.
- `LectorArchivo`: transforma registros del archivo de texto en objetos y guarda nuevas entidades.
- `Registrable`: define el comportamiento común de las entidades administrables.

## Requisitos

- JDK 23.
- IntelliJ IDEA u otro entorno compatible con proyectos Maven.

## Clonar y ejecutar con IntelliJ IDEA

1. Clona el repositorio:

   ```bash
   git clone https://github.com/gquinterosprog/LlanquihueTourapp.git
   ```

2. Abre la carpeta `LlanquihueTourapp` en IntelliJ IDEA.
3. Configura el SDK del proyecto con Java 23.
4. Ejecuta la clase `ui.Main`.
5. Utiliza el menú para registrar, buscar o visualizar entidades.

## Compilar desde una terminal

En Windows PowerShell, desde la raíz del proyecto:

```powershell
New-Item -ItemType Directory -Force out
javac -encoding UTF-8 -d out (Get-ChildItem src/main/java -Recurse -Filter *.java).FullName
java -cp out ui.Main
```

El proyecto también incluye un archivo `pom.xml` para importarlo como proyecto Maven.

## Persistencia

El archivo `datos.txt` se entrega vacío. Los datos se incorporan únicamente cuando el usuario registra entidades desde la interfaz gráfica y permanecen almacenados después de cerrar el programa.

Los registros utilizan campos separados por punto y coma. `GestorEntidades` transforma cada objeto en una línea de texto y `LectorArchivo` la guarda. Al volver a iniciar el programa, `LectorArchivo` valida y reconstruye los objetos guardados; las líneas inválidas se omiten y se informa el motivo por consola.

## Presentación de la ETF

Para el video de máximo cinco minutos se recomienda mostrar:

1. La estructura de paquetes.
2. La jerarquía de `Persona` y la interfaz `Registrable`.
3. El registro de una entidad con sus datos completos.
4. La validación de un RUT incorrecto.
5. La búsqueda y el reporte polimórfico.
6. El contenido persistido en `datos.txt` y su carga al reiniciar.
