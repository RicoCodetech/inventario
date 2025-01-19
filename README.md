# Sistema de Inventario de Bebidas Alcohólicas

## Introducción
Este proyecto implementa un sistema de inventario básico para gestionar bebidas alcohólicas almacenadas en un entorno local. El sistema permite realizar operaciones CRUD (crear, leer, actualizar y borrar) sobre los productos, utilizando una base de datos MySQL como almacenamiento y una interfaz basada en línea de comandos, a modo de ejemplo tambien existe la clase GestionProductos que seria la implementacion usando una estructura de datos.

## Características principales
- **Inserción de productos**: Permite agregar nuevas bebidas en secciones predefinidas como Ron, Vodka, etc.
- **Actualización de productos**: Incrementa o reduce la cantidad de unidades de un producto, asegurando que nunca se registren números negativos.
- **Consulta de productos**: Busca productos específicos por nombre y muestra su cantidad y sección.
- **Consulata productos por seccion**: Lista todos los productos de una categoria determinada de bebidas.
- **Listado de productos**: Muestra todos los productos o aquellos filtrados por sección.
- **Eliminación de productos**: Borra productos existentes por su nombre.

## Tecnologías utilizadas
- **Lenguaje de programación**: Java
- **Base de datos**: MySQL
- **Metodología**: Desarrollo modular basado en abstracciones
- **Entorno operativo**: Windows CMD o terminal Linux

## Requisitos del sistema
- Java 8 o superior
- Servidor MySQL configurado
- PowerShell o terminal equivalente

## Instalación
1. **Configurar la base de datos**:
   - Crear una base de datos MySQL con las credenciales necesarias.
   - Ejecutar el script SQL proporcionado en el archivo `schema.sql` para configurar las tablas.

2. **Configurar el proyecto**:
   - Clonar este repositorio:  
     ```bash
     git clone <URL_DEL_REPOSITORIO>
     ```
   - Compilar el código fuente:  
     ```bash
     javac -d bin src/*.java
     ```

3. **Configurar la conexión a la base de datos**:
   - Editar la clase `Conexion.java` para agregar las credenciales de tu base de datos:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/nombre_base_datos";
     private static final String USER = "usuario";
     private static final String PASSWORD = "contraseña";
     ```

4. **Ejecutar el programa**:
   - Iniciar el programa desde la terminal:  
     ```bash
     java -cp bin Main
     ```

## Uso
El programa presenta un menú con las siguientes opciones:
1. **Insertar producto**: Agrega una nueva bebida.
2. **Modificar producto**: Actualiza la cantidad de unidades.
3. **Consultar producto**: Busca un producto específico.
4. **Listar productos de almacén**: Muestra todos los productos registrados.
5. **Listar productos por seccion** Lista los productos por su categoria
6. **Eliminar un producto**: Elimina un producto por su nombre.
7. **Salir del programa**: Finaliza la ejecución.

## Organización del código
```
├── src/
│   ├── Conexion.java         # Clase para manejar la conexión a la base de datos
│   ├── GestionBaseDatos.java # Clase con los métodos CRUD
│   ├── Main.java             # Clase principal que ejecuta el programa
├── schema.sql                # Script para inicializar la base de datos
├── README.md                 # Documentación del proyecto
```

## Restricciones
- Los nombres de los productos deben ser únicos.
- La cantidad de unidades no puede ser negativa.
- Solo se permiten secciones predefinidas como Ron, Vodka, etc.

## Próximos pasos
- Implementar una interfaz gráfica para mejorar la experiencia del usuario.
- Integrar un lector de códigos de barras para gestionar productos mediante un identificador único.
- Ampliar la funcionalidad para interactuar con sistemas ERP.

## Contribuciones
Si deseas contribuir a este proyecto:
1. Haz un fork del repositorio.
2. Crea una rama con tus cambios: `git checkout -b nueva-funcionalidad`.
3. Realiza un pull request.
---

Para más información o dudas, contacta al desarrollador: **[Oscar Rico Martinez]**
