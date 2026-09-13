package inventario;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        GestionBaseDatos gesDatos = new GestionBaseDatos(conexion);
        DatosEntrada entrada = new DatosEntrada();
        boolean salir = false;

        while (!salir) {
            System.out.println("Sistema de inventario de tienda");
            System.out.println();
            System.out.println("Menú:");
            System.out.println("1.- Insertar producto");
            System.out.println("2.- Modificar producto");
            System.out.println("3.- Consultar producto");
            System.out.println("4.- Listar productos del almacén");
            System.out.println("5.- Eliminar un producto");
            System.out.println("6.- Listar productos por categoría");
            System.out.println("7.- Salir del programa");

            int option = entrada.opcion();

            switch (option) {
                case 1: {
                    Section section = entrada.section();
                    String name = entrada.nombre();
                    int amount = entrada.amount();

                    if (!gesDatos.existeProducto(name)) {
                        Producto producto = new Producto(section, name, amount);
                        gesDatos.insertarProducto(producto);
                    } else {
                        System.out.println("El producto ya existe, no se almacenó.");
                    }
                    break;
                }

                case 2: {
                    String name = entrada.nombre();
                    int amount = entrada.amount();
                    if (gesDatos.actualizarCantidadProducto(name, amount)) {
                        System.out.println("Producto actualizado.");
                    } else {
                        System.out.println("Operación no realizada.");
                    }
                    break;
                }

                case 3: {
                    Producto producto = gesDatos.leerProducto(entrada.nombre());
                    if (producto != null) {
                        System.out.println("Nombre: " + producto.getName() + ", Sección: " + producto.getSection() + ", Cantidad: " + producto.getAmount());
                    } else {
                        System.out.println("Producto no encontrado en el almacén.");
                    }
                    break;
                }

                case 4: {
                    List<Producto> lista = gesDatos.listarProductos();
                    for (Producto producto : lista) {
                        System.out.println("Nombre: " + producto.getName() + ", Sección: " + producto.getSection() + ", Cantidad: " + producto.getAmount());
                    }
                    break;
                }

                case 5: {
                    String name = entrada.nombre();
                    if (gesDatos.deleteProducto(name)) {
                        System.out.println("Producto eliminado del almacén.");
                    } else {
                        System.out.println("Operación no realizada.");
                    }
                    break;
                }

                case 6: {
                    List<Producto> lista = gesDatos.getProductosSection(entrada.section().name());
                    for (Producto producto : lista) {
                        System.out.println("Nombre: " + producto.getName() + ", Sección: " + producto.getSection() + ", Cantidad: " + producto.getAmount());
                    }
                    break;
                }

                case 7:
                    conexion.cerrarConexion();
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}