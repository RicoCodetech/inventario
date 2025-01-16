package inventario;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		GestionBaseDatos gesDatos = new GestionBaseDatos(conexion);
		DatosEntrada entrada = new DatosEntrada();
		boolean salir = false;
		
			while(!salir) {
				System.out.println("Sistema de inventario para bebidas alcoholicas");
				System.out.println();
				System.out.println("Menu");
				System.out.println("1.- Insertar producto");
				System.out.println("2.- Modificar producto");
				System.out.println("3.- Consultar producto");
				System.out.println("4.- Listar productos de almacem");
				System.out.println("5.- Eliminar un producto");
				System.out.println("6.- Listar producto por categoria");
				System.out.println("7.- Salir del almacen");
				
				String name;
				int amount;
				int option = entrada.opcion();
				
				switch (option) {
				//Insertar productos
				case 1: 
					Section section = entrada.section();
					name = entrada.nombre();
					amount = entrada.amount();
					
					if(!gesDatos.existeProducto(name)) {
						Producto producto = new Producto(section, name, amount);
						gesDatos.insertarProducto(producto);
					}else {
						System.out.println("Producto no almacenado.");
					}

					break;
					
				//Modificar producto	
				case 2:
					name = entrada.nombre();
					amount = entrada.amount();
					if(gesDatos.actualizarCantidadProducto(name, amount)) {
						System.out.println("Producto actualizado");
					}else {
						System.out.println("Operacion no realizada");
					}
					break;
					
				//Consultar un producto	
				case 3:
					Producto producto = gesDatos.leerProducto(entrada.nombre());
					if(producto != null) {
						System.out.println("Nombre: " + producto.getName() + ", Seccion: " + producto.getSection() + ", Cantidad: " + producto.getAmount());
					}else {
						System.out.println("Producto no encontrado en el almacen");
					}
				
					break;
					
				//Listar productos de almacen	
				case 4:
					ArrayList<Producto> lista = gesDatos.listarProductos();
					Iterator<Producto> iterador = lista.iterator();
					while(iterador.hasNext()) {
						Producto product = iterador.next();
						System.out.println("Nombre: " + product.getName() + ", Seccion: " + product.getSection() + ", Cantidad: " + product.getAmount());
					}
					break;
					
				//Eliminar un producto
				case 5:
					name = entrada.nombre();
					if(gesDatos.delateProducto(name)) {
						System.out.println("Producto eliminado del almacen");
					}else {
						System.out.println("Operacion no realizada");
					}
					gesDatos.delateProducto(name);
					
					break;
					
				//Listar productos por categoria	
				case 6:
					ArrayList<Producto> lista2 = gesDatos.getProductosSection(entrada.nombre());
					Iterator<Producto> iterador2 = lista2.iterator();
					while(iterador2.hasNext()) {
						Producto product = iterador2.next();
						System.out.println("Nombre: " + product.getName() + ", Seccion: " + product.getSection() + ", Cantidad: " + product.getAmount());
					}
					break;
					
				//Salir	
				case 7:
					conexion.cerrarConexion();
					salir = true;
				}
			}
	}

}
