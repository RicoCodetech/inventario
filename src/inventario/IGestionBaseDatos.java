package inventario;


import java.util.List;

public interface IGestionBaseDatos {
    /**
     * Comprueba si un producto existe en la base de datos.
     * 
     * @param nombre Nombre del producto.
     * @return true si el producto existe, false en caso contrario.
     */
    boolean existeProducto(String nombre);

    /**
     * Inserta un producto en la base de datos.
     * 
     * @param producto Objeto Producto a insertar.
     * @return true si el producto se insertó correctamente, false en caso contrario.
     */
    boolean insertarProducto(Producto producto);

    /**
     * Elimina un producto de la base de datos.
     * 
     * @param nombre Nombre del producto a eliminar.
     * @return true si el producto se eliminó correctamente, false en caso contrario.
     */
    boolean delateProducto(String nombre);

    /**
     * Actualiza un producto en la base de datos.
     * 
     * @param nombre Nombre del producto.
     * @param nuevoAmount Nuevo valor de cantidad (amount) para el producto.
     * @return true si el producto se actualizó correctamente, false en caso contrario.
     */
    boolean actualizarCantidadProducto(String nombre, int nuevoAmount);

    /**
     * Lista todos los productos almacenados en la base de datos.
     * 
     * @return Lista de productos.

     */
    List<Producto> listarProductos();

    /**
     * Lee la información de un producto específico.
     * 
     * @param nombre Nombre del producto a leer.
     * @return Objeto Producto si se encuentra, null en caso contrario.
     */
    Producto leerProducto(String nombre);
}
