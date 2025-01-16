package inventario;

import java.util.ArrayList;
import java.util.Iterator;


public class GestionProductos {
	private ArrayList <Producto> lista;
	DatosEntrada entrada = new DatosEntrada();
	public GestionProductos() {
		// TODO Auto-generated constructor stub
		lista = new ArrayList<>();
	}
	
	//Comprueba si el producto esta en la lista
	private boolean existeProducto(String name) {
		Iterator<Producto> iterador = lista.iterator();
		while(iterador.hasNext()) {
			Producto producto = iterador.next();
			if(producto.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	//Inserta un producto que no existe en la lista
	public boolean insertarProducto() {
		boolean correcto=false;
		Section section = null;
		
			//Insertar producto en la lista
			while(!correcto) {
				
		        try {
		            section = Section.valueOf(entrada.nombre());  // Convertir a un valor del enum
		            System.out.println("Sección seleccionada: " + section);
		            correcto = true;
		        } catch (IllegalArgumentException e) {
		            System.out.println("Error: La sección ingresada no es válida.");
		        }
			}
			
			String name = entrada.nombre();
			
			if(existeProducto(name)) {
				return false;
			}
			
			int amount = entrada.amount();
			
	        Producto productoInsertado = new Producto(section,name,amount);
	        lista.add(productoInsertado);
			return true;
		
	}
	
	//Acutaliza la cantidad de unidades de un producto sin estar nunca en negativo
	public boolean updateProducto() {

			Iterator <Producto> iterador = lista.iterator();
			String name = entrada.nombre();
			int amount = entrada.amount();
			
			while(iterador.hasNext()) {
				Producto producto = iterador.next();
				if(producto.getName().equals(name)) {
					if((producto.getAmount() + amount) < 0) {
						return false;
					}
					producto.setamount(amount);
					return true;
				}
			}
		return false;
	}
	
	//devuelve el producto nombre sino devuelve null
	public Producto leerProducto(String nombre) {
		Iterator <Producto> iterador = lista.iterator();
		Producto producto = null;
		while(iterador.hasNext()) {
			producto = iterador.next();
			if(producto.getName().equals(nombre)) {
				return producto;
			}
			producto = null;
		}
		return producto;
	}
	
	//Elimina producto existente en almacen
	public boolean eliminarProducto(String nombre) {
		Iterator <Producto> iterador = lista.iterator();
		int indice=0;
		while(iterador.hasNext()) {
			Producto producto=iterador.next();
			if(producto.getName().equals(nombre)) {
				lista.remove(indice);
				return true;
			}
			indice++;
		}
		return false;
	}
	//devuelve el inventario
	public ArrayList <Producto> listarAlmacen(){
		return lista;
	}
}
