package unittest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.db.DB;
import src.db.DbException;
import src.model.entities.ProdEntities.Category;
import src.model.entities.ProdEntities.Product;
import src.model.enums.ProductAvailability;
import src.model.enums.TypeProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductTableTest {

    private static Product product;

    private static Connection connection;

    private static PreparedStatement statement;

    private static ResultSet set;

    // String name, Double price, Integer quantity, ProductAvailability availability, Category cat
    // Integer id, TypeProduct type, Integer tier

    @BeforeEach
    void setUp() {

        Category category = new Category(1, TypeProduct.ELECTRONICS, 1);

        product = new Product("OK COMPUTER", 100.0, 8, ProductAvailability.IN_STOCK, category);

        connection = DB.getConnection();

        statement = null;

        set = null;

    }

    @AfterAll
    static void tearDown() {

        DB.closeConnections(connection);

        DB.closeStatements(statement);

        DB.closeResult(set);

    }

    @Test
    void insert() {

        try {

            statement = connection.prepareStatement(
                    "INSERT INTO \"Stock\".\"ProductTest\" " +
                            "(name, price, quantity, availability, id_test_category)" +
                            " VALUES (?,?,?,?,?)"
            );

            statement.setString(1, product.getName());

            statement.setDouble(2, product.getPrice());

            statement.setInt(3, product.getQuantity());

            statement.setString(4, product.getAvailability().name());

            statement.setInt(5, product.getCategory().getId());

            int rowsAffected = statement.executeUpdate();

            assertEquals(1, rowsAffected);

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Test
    void display() {

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM \"Stock\".\"ProductTest\""
            );

            set = statement.executeQuery();

            assertTrue(set.next());

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Test
    void updateName() {

        try {

            statement = connection.prepareStatement(
                    "UPDATE \"Stock\".\"ProductTest\" set name = ? WHERE id = ?"
            );

            statement.setString(1, "NO COMPUTER");

            statement.setInt(2, 1);

            int rowsAffected = statement.executeUpdate();

            assertEquals(1, rowsAffected);

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Test
    void updatePrice() {

        try {

            statement = connection.prepareStatement(
                    "UPDATE \"Stock\".\"ProductTest\" set price = ? WHERE id = ?"
            );

            statement.setDouble(1, 500.0);

            statement.setInt(2, 1);

            int rowsAffected = statement.executeUpdate();

            assertEquals(1, rowsAffected);

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

}