package inventario;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestionBaseDatosTest {

    private Connection connection;
    private GestionBaseDatos gestion;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL", "sa", "");
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE productos (" +
                    "name VARCHAR(255) NOT NULL PRIMARY KEY, " +
                    "section VARCHAR(255) NOT NULL, " +
                    "amount INT NOT NULL CHECK (amount >= 0))");
        }
        gestion = new GestionBaseDatos(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    void insertarProductoYLuegoLeerloDevuelveLosMismosDatos() {
        Producto producto = new Producto(Section.ALIMENTOS, "Arroz", 50);

        boolean insertado = gestion.insertarProducto(producto);

        assertTrue(insertado);
        Producto leido = gestion.leerProducto("Arroz");
        assertNotNull(leido);
        assertEquals("Arroz", leido.getName());
        assertEquals(Section.ALIMENTOS, leido.getSection());
        assertEquals(50, leido.getAmount());
    }

    @Test
    void insertarProductoDuplicadoEsRechazado() {
        gestion.insertarProducto(new Producto(Section.BEBIDAS, "Refresco", 10));

        boolean duplicado = gestion.insertarProducto(new Producto(Section.BEBIDAS, "Refresco", 5));

        assertFalse(duplicado);
    }

    @Test
    void actualizarCantidadIncrementaLaCantidadExistente() {
        gestion.insertarProducto(new Producto(Section.ALIMENTOS, "Leche", 20));

        boolean actualizado = gestion.actualizarCantidadProducto("Leche", 10);

        assertTrue(actualizado);
        assertEquals(30, gestion.leerProducto("Leche").getAmount());
    }

    @Test
    void actualizarCantidadQueDariaNegativoEsRechazada() {
        gestion.insertarProducto(new Producto(Section.ALIMENTOS, "Pan", 5));

        boolean actualizado = gestion.actualizarCantidadProducto("Pan", -10);

        assertFalse(actualizado);
        assertEquals(5, gestion.leerProducto("Pan").getAmount());
    }

    @Test
    void actualizarCantidadDeProductoInexistenteDevuelveFalse() {
        assertFalse(gestion.actualizarCantidadProducto("Inexistente", 3));
    }

    @Test
    void eliminarProductoExistenteDevuelveTrue() {
        gestion.insertarProducto(new Producto(Section.OTROS, "Cinta", 2));

        assertTrue(gestion.deleteProducto("Cinta"));
        assertNull(gestion.leerProducto("Cinta"));
    }

    @Test
    void eliminarProductoInexistenteDevuelveFalse() {
        assertFalse(gestion.deleteProducto("Inexistente"));
    }

    @Test
    void listarProductosDevuelveTodosLosRegistrados() {
        gestion.insertarProducto(new Producto(Section.ALIMENTOS, "Azúcar", 30));
        gestion.insertarProducto(new Producto(Section.ELECTRONICA, "Cable", 12));

        List<Producto> productos = gestion.listarProductos();

        assertEquals(2, productos.size());
    }

    @Test
    void leerProductoInexistenteDevuelveNull() {
        assertNull(gestion.leerProducto("No existe"));
    }

    @Test
    void getProductosSectionFiltraPorSeccion() {
        gestion.insertarProducto(new Producto(Section.ALIMENTOS, "Arroz", 8));
        gestion.insertarProducto(new Producto(Section.BEBIDAS, "Agua", 6));
        gestion.insertarProducto(new Producto(Section.ALIMENTOS, "Frijol", 15));

        List<Producto> alimentos = gestion.getProductosSection("ALIMENTOS");

        assertEquals(2, alimentos.size());
    }
}