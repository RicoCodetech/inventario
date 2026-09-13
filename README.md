# Sistema de Inventario de Tienda

[![Java](https://img.shields.io/badge/Java-8+-orange)](https://www.java.com)
[![Maven](https://img.shields.io/badge/Maven-3.9-blue)](https://maven.apache.org/)
[![Build](https://github.com/RicoCodetech/inventario/actions/workflows/ci.yml/badge.svg)](https://github.com/RicoCodetech/inventario/actions)
[![JUnit](https://img.shields.io/badge/tests-JUnit%205-green)](https://junit.org/junit5/)
[![Licencia](https://img.shields.io/badge/licencia-MIT-green)](LICENSE)

## Introducción
Aplicación de línea de comandos para gestionar el inventario de una tienda. Permite realizar operaciones CRUD (crear, leer, actualizar y borrar) sobre los productos, utilizando MySQL como almacenamiento y una arquitectura modular basada en una interfaz y su implementación.

## Características principales
- **Inserción de productos**: agrega productos en secciones predefinidas (Alimentos, Bebidas, Electrónica, etc.).
- **Actualización de productos**: incrementa o reduce la cantidad de unidades, evitando cantidades negativas.
- **Consulta de productos**: busca productos por nombre y muestra su cantidad y sección.
- **Listado de productos**: muestra todos los productos o los filtra por sección.
- **Eliminación de productos**: borra productos existentes por su nombre.
- **Tests unitarios**: cobertura de la capa de datos con JUnit 5 y H2 en memoria.

## Tecnologías utilizadas
- **Lenguaje**: Java 8 o superior
- **Build**: Maven
- **Base de datos**: MySQL
- **Tests**: JUnit 5 + H2 (base de datos en memoria)

## Requisitos del sistema
- JDK 8 o superior
- Maven 3.6+
- Servidor MySQL configurado

## Instalación

### 1. Configurar la base de datos
```bash
mysql -u root -p < schema.sql
```

El script crea la base de datos `inventario` con la tabla `productos`:

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

### 3. Compilar y ejecutar
```bash
# Ejecutar los tests
mvn test

# Compilar el proyecto
mvn package

# Ejecutar la aplicación (el JAR incluye las dependencias del runtime)
java -jar target/inventario-tienda-1.0.0.jar
```

> Nota: `mvn package` genera un JAR que requiere las dependencias en el classpath. Para un ejecutable autocontenido se puede usar el plugin `maven-shade-plugin`.

## Uso
El programa presenta un menú con las siguientes opciones:
1. **Insertar producto**: agrega un nuevo producto.
2. **Modificar producto**: actualiza la cantidad de unidades (positiva o negativa).
3. **Consultar producto**: busca un producto específico.
4. **Listar productos de la tienda**: muestra todos los productos registrados.
5. **Eliminar un producto**: elimina un producto por su nombre.
6. **Listar productos por categoría**: lista los productos de una sección determinada.
7. **Salir del programa**: cierra la conexión y finaliza la aplicación.

## Organización del código
```
├── src/
│   ├── main/java/inventario/
│   │   ├── Conexion.java          # Gestión de la conexión con MySQL
│   │   ├── DatosEntrada.java      # Entrada y validación de datos del usuario
│   │   ├── GestionBaseDatos.java  # Implementación del CRUD
│   │   ├── IGestionBaseDatos.java # Interfaz con la definición del CRUD
│   │   ├── Main.java              # Clase principal (menú de la CLI)
│   │   ├── Producto.java          # Modelo de datos del producto
│   │   └── Section.java           # Categorías de productos permitidas (enum)
│   └── test/java/inventario/
│       └── GestionBaseDatosTest.java # Tests unitarios de la capa de datos
├── docs/requeriments/             # Documento de requerimientos (SRS)
├── schema.sql                     # Script para inicializar la base de datos
├── pom.xml                        # Configuración de Maven
└── README.md                      # Documentación del proyecto
```

## Restricciones
- Los nombres de los productos son únicos.
- La cantidad de unidades no puede ser negativa.
- Solo se permiten las secciones predefinidas: ALIMENTOS, BEBIDAS, LIMPIEZA, ELECTRONICA, ROPA, FARMACIA y OTROS.

## Próximos pasos
- Implementar una interfaz gráfica (JavaFX) para mejorar la experiencia del usuario.
- Integrar un lector de códigos de barras para gestionar productos mediante un identificador único.
- Ampliar la funcionalidad para interactuar con sistemas ERP.
- Generar un JAR autocontenido con Maven Shade.

## Contribuciones
Si deseas contribuir a este proyecto:
1. Haz un fork del repositorio.
2. Crea una rama con tus cambios: `git checkout -b nueva-funcionalidad`.
3. Realiza un pull request.

---

Para más información o dudas, contacta al desarrollador: **[Oscar Rico Martinez]**