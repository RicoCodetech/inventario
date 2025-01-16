package inventario;

public class Sistema {

	public Sistema() {
		// TODO Auto-generated constructor stub
	}
	public static void limpiarPantalla() {
	    try {
	        if (System.getProperty("os.name").contains("Windows")) {
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        } else {
	            System.out.print("\033[H\033[2J");
	            System.out.flush();
	        }
	    } catch (Exception e) {
	        System.out.println("No se pudo limpiar la pantalla.");
	    }
	}

}
