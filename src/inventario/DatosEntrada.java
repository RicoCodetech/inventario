package inventario;

import java.util.Scanner;

public class DatosEntrada {

    private final Scanner scanner = new Scanner(System.in);

    public int opcion() {
        while (true) {
            System.out.print("Selecciona una opción: ");
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                return opcion;
            }
            System.out.println("Entrada inválida, debes ingresar el número de una opción.");
            scanner.nextLine();
        }
    }

    public String nombre() {
        System.out.print("Nombre de la bebida: ");
        return scanner.nextLine();
    }

    public Section section() {
        while (true) {
            System.out.print("Sección: ");
            String seccion = scanner.nextLine();

            try {
                Section section = Section.valueOf(seccion.toUpperCase());
                System.out.println("Sección seleccionada: " + section);
                return section;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: la sección ingresada no es válida. Inténtalo de nuevo.");
            }
        }
    }

    public int amount() {
        int amount = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Unidades del producto: ");
            if (scanner.hasNextInt()) {
                amount = scanner.nextInt();
                entradaValida = true;
            } else {
                System.out.println("Entrada inválida, debes ingresar un número entero.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return amount;
    }
}