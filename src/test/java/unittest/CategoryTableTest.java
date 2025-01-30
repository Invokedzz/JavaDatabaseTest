package unittest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.db.DB;
import src.db.DbException;
import src.model.enums.TypeProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTableTest {

    private static Connection connection;

    private static PreparedStatement statement;

    private static ResultSet set;

    @BeforeEach
    void setUp() {

        connection = DB.getConnection();

    }

    @AfterAll
    static void tearDown() {

        DB.closeConnections(connection);

        DB.closeStatements(statement);

        DB.closeResult(set);

    }

    @Test
    @DisplayName("INSERT test")
    void insert() {

        try {

            statement = connection.prepareStatement(
                    "INSERT INTO \"Stock\".\"TestCategory\" " +
                            "(tier, typeproduct)" +
                            " VALUES (?, ?)"
            );

            statement.setInt(1, 3);

            statement.setString(2, TypeProduct.TOOLS.name());

            int rowsAffected = statement.executeUpdate();

            assertEquals(1, rowsAffected);

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }

    @Test
    @DisplayName("SELECT * test")
    void display() {

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM \"Stock\".\"TestCategory\""
            );

            set = statement.executeQuery();

            assertTrue(set.next());

            while (set.next()) {

                Integer tier = set.getInt("tier");

                String typeProduct = set.getString("typeproduct");

                System.out.println(tier + " " + typeProduct);

            }

        } catch (SQLException exception) {

            throw new DbException(exception.getMessage());

        }

    }


}