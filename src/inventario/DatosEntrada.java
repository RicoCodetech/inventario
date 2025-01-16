package inventario;

import java.util.Scanner;

public class DatosEntrada {
	Scanner scanner = new Scanner(System.in);
	
	public int opcion() {
		System.out.println("Selecciona una opcion: ");
		int opcion = scanner.nextInt();
		scanner.nextLine();
		return opcion;
	}

	public String nombre() {
		System.out.println("Nombre de la bebida: ");
		String nombre = scanner.nextLine();
		return nombre;
	}
	
	public Section section() {
	    boolean correcto = false;
	    Section section = null; // Inicializamos fuera del bucle

	    while (!correcto) {
	        System.out.println("Seccion: ");
	        String seccion = scanner.nextLine();

	        try {
	            section = Section.valueOf(seccion.toUpperCase()); // Convertimos a mayúsculas para coincidir con el enum
	            System.out.println("Sección seleccionada: " + section);
	            correcto = true; // Salimos del bucle
	        } catch (IllegalArgumentException e) {
	            System.out.println("Error: La sección ingresada no es válida. Inténtalo de nuevo.");
	        }
	    }

	    return section; // Devolvemos la sección válida
	}
	
	public int amount() {
		boolean entradaValida = false;
		int amount=0;
		
		while(!entradaValida) {
			System.out.println("Unidades del producto: ");
			try {
				amount = scanner.nextInt();
				entradaValida=true;
			} catch (Exception e) {
				System.out.println("Entrada invalida, debe ingresar un numero entero. ");
			}
		}
		//scanner.close();
		return amount;
	}
	
}
