# Sistema de Inventario de Bebidas Alcohólicas

## Introducción
Este proyecto implementa un sistema de inventario para gestionar bebidas alcohólicas almacenadas de forma local. El sistema permite realizar operaciones CRUD (crear, leer, actualizar y borrar) sobre los productos, utilizando una base de datos MySQL como almacenamiento y una interfaz basada en línea de comandos.

## Características principales
- **Inserción de productos**: Agrega nuevas bebidas en secciones predefinidas como Ron, Vodka, etc.
- **Actualización de productos**: Incrementa o reduce la cantidad de unidades de un producto, evitando que se registren cantidades negativas.
- **Consulta de productos**: Busca productos por nombre y muestra su cantidad y sección.
- **Listado de productos**: Muestra todos los productos o los filtra por sección.
- **Eliminación de productos**: Borra productos existentes por su nombre.

## Tecnologías utilizadas
- **Lenguaje de programación**: Java 8 o superior
- **Base de datos**: MySQL
- **Metodología**: Desarrollo modular basado en abstracciones (interfaz + implementación)
- **Entorno operativo**: Windows CMD/PowerShell o terminal Linux

## Requisitos del sistema
- JDK 8 o superior
- Servidor MySQL configurado
- Conector JDBC de MySQL (mysql-connector-j) en el classpath en tiempo de ejecución

## Instalación

### 1. Configurar la base de datos
1. Crear la base de datos y la tabla ejecutando el script `schema.sql`:
   ```bash
   mysql -u root -p < schema.sql
   ```
2. El script crea la base de datos `inventario` con la tabla `productos`:
   ```sql
   CREATE DATABASE IF NOT EXISTS inventario;
   USE inventario;

   CREATE TABLE IF NOT EXISTS productos (
       name VARCHAR(255) NOT NULL PRIMARY KEY,
       section VARCHAR(255) NOT NULL,
       amount INT NOT NULL CHECK (amount >= 0)
   );
   ```

### 2. Configurar la conexión
La clase `Conexion.java` lee la configuración desde variables de entorno:

```bash
# Linux / macOS
export DB_URL="jdbc:mysql://localhost:3306/inventario"
export DB_USER="tu_usuario"
export DB_PASSWORD="tu_contraseña"

# Windows (PowerShell)
$env:DB_URL="jdbc:mysql://localhost:3306/inventario"
$env:DB_USER="tu_usuario"
$env:DB_PASSWORD="tu_contraseña"
```

Si no se definen, se usan los valores por defecto (`root` sin contraseña y la base `inventario`).

### 3. Compilar el proyecto
```bash
javac -d bin src/inventario/*.java
```

### 4. Ejecutar el programa
Añade el conector JDBC de MySQL a la ruta de clases:

```bash
# Linux / macOS
java -cp "bin:lib/mysql-connector-j.jar" inventario.Main

# Windows
java -cp "bin;lib\mysql-connector-j.jar" inventario.Main
```

## Uso
El programa presenta un menú con las siguientes opciones:
1. **Insertar producto**: Agrega una nueva bebida.
2. **Modificar producto**: Actualiza la cantidad de unidades (positiva o negativa).
3. **Consultar producto**: Busca un producto específico.
4. **Listar productos del almacén**: Muestra todos los productos registrados.
5. **Eliminar un producto**: Elimina un producto por su nombre.
6. **Listar productos por categoría**: Lista los productos de una sección determinada.
7. **Salir del almacén**: Cierra la conexión y finaliza el programa.

## Organización del código
```
├── src/
│   └── inventario/
│       ├── Conexion.java          # Gestión de la conexión con MySQL
│       ├── DatosEntrada.java      # Entrada y validación de datos del usuario
│       ├── GestionBaseDatos.java  # Implementación del CRUD
│       ├── IGestionBaseDatos.java # Interfaz con la definición del CRUD
│       ├── Main.java              # Clase principal (menú de la CLI)
│       ├── Producto.java          # Modelo de datos del producto
│       └── Section.java           # Categorías de bebidas permitidas (enum)
├── docs/requeriments/             # Documento de requerimientos (SRS)
├── schema.sql                     # Script para inicializar la base de datos
└── README.md                      # Documentación del proyecto
```

## Restricciones
- Los nombres de los productos son únicos.
- La cantidad de unidades no puede ser negativa.
- Solo se permiten las secciones predefinidas: VINO, RON, VODKA, WHISKEY, BRANDY, LICOR y OTROS.

## Próximos pasos
- Implementar una interfaz gráfica para mejorar la experiencia del usuario.
- Integrar un lector de códigos de barras para gestionar productos mediante un identificador único.
- Añadir pruebas unitarias (JUnit) para los métodos de la capa de datos.
- Ampliar la funcionalidad para interactuar con sistemas ERP.

## Contribuciones
Si deseas contribuir a este proyecto:
1. Haz un fork del repositorio.
2. Crea una rama con tus cambios: `git checkout -b nueva-funcionalidad`.
3. Realiza un pull request.

---

Para más información o dudas, contacta al desarrollador: **[Oscar Rico Martinez]**