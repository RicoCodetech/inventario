package inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestionBaseDatos implements IGestionBaseDatos {
	private Connection connection;

    public GestionBaseDatos(Conexion conexion) {
        this.connection = conexion.getConexion();
    }
	
    public boolean actualizarCantidadProducto(String name, int cambioCantidad) {
        // Consulta para obtener el valor actual de amount
        String consultaActual = "SELECT amount FROM productos WHERE name = ?";
        String queryActualizacion = "UPDATE productos SET amount = amount + ? WHERE name = ?";

        try (PreparedStatement stmtConsulta = connection.prepareStatement(consultaActual)) {
            stmtConsulta.setString(1, name);

            try (ResultSet rs = stmtConsulta.executeQuery()) {
                if (rs.next()) {
                    int cantidadActual = rs.getInt("amount");

                    // Verificar si la nueva cantidad será negativa
                    if (cantidadActual + cambioCantidad < 0) {
                        System.out.println("Error: La cantidad no puede ser negativa.");
                        return false; // Salir si el resultado sería negativo
                    }
                } else {
                    System.out.println("El producto no existe.");
                    return false; // Salir si no se encontró el producto
                }
            }

            // Si la cantidad no será negativa, realiza la actualización
            try (PreparedStatement stmtActualizacion = connection.prepareStatement(queryActualizacion)) {
                stmtActualizacion.setInt(1, cambioCantidad);
                stmtActualizacion.setString(2, name);

                int filasActualizadas = stmtActualizacion.executeUpdate();
                return filasActualizadas > 0; // Devuelve true si se actualizó al menos una fila
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar cantidad del producto: " + e.getMessage());
            return false;
        }
    }


    
    public boolean existeProducto(String nombre) {
        String query = "SELECT COUNT(*) FROM productos WHERE name = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre); // Sustituye "?" por el nombre

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // Lee el número de filas que coinciden
                    return count > 0; // Devuelve true si hay al menos un resultado
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el producto: " + e.getMessage());
        }

        return false; // Devuelve false si hubo un error o no se encontró
    }
	
    
    
    public boolean insertarProducto(Producto producto) {
        String sql = "INSERT INTO productos (name, section, amount) VALUES (?, ?, ?)";
        if(!existeProducto(producto.getName())) {
        	
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, producto.getName());
	            stmt.setString(2, producto.getSection().name()); // Enum convertido a String
	            stmt.setInt(3, producto.getAmount());
	            System.out.println("Producto introducido en Base de Datos");
	            int filasInsertadas = stmt.executeUpdate();
	            return filasInsertadas > 0;
	        } catch (SQLException e) {
	            System.out.println("Error al insertar producto: " + e.getMessage());
	            return false;
	        }
        }else {
        	System.out.println("El producto ya existe en la base de datos.");
        	return false;
        }
    }

	
	
	
	public boolean delateProducto(String nombre) {
		String sql = "DELETE FROM productos WHERE name = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, nombre);
	        int filasEliminadas = stmt.executeUpdate();
	        return filasEliminadas > 0;
	        
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar producto: " + e.getMessage());
	        return false;
	    }
	}
	
	public ArrayList<Producto> listarProductos() {
	    ArrayList<Producto> productos = new ArrayList<>();
	    String sql = "SELECT * FROM productos";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String name = rs.getString("name");
	            Section section = Section.valueOf(rs.getString("section"));
	            int amount = rs.getInt("amount");
	            productos.add(new Producto(section, name, amount));    
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al listar productos: " + e.getMessage());
	    }

	    return productos;
	}
	
	public Producto leerProducto(String nombre) {
		
		String sql = "SELECT * FROM productos WHERE name = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(sql);) {
	        stmt.setString(1, nombre);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            String name = rs.getString("name");
	            Section section = Section.valueOf(rs.getString("section"));
	            int amount = rs.getInt("amount");

	            return new Producto(section, name, amount);
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al leer producto: " + e.getMessage());
	    }
	    return null;
	}
	
	public ArrayList<Producto> getProductosSection(String section){
	    ArrayList<Producto> productos = new ArrayList<>();
	    String sql = "SELECT * FROM productos WHERE section = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	    	stmt.setString(1, section);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String name = rs.getString("name");
	            Section seccion = Section.valueOf(rs.getString("section"));
	            int amount = rs.getInt("amount");
	            
	            productos.add(new Producto(seccion, name, amount));    
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al listar productos: " + e.getMessage());
	    }

	    return productos;
	}


	
}
